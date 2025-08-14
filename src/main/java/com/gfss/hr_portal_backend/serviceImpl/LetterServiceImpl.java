package com.gfss.hr_portal_backend.serviceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;
import com.gfss.hr_portal_backend.entity.Letter;
import com.gfss.hr_portal_backend.repository.EmployeeRepository;
import com.gfss.hr_portal_backend.repository.LetterRepository;
import com.gfss.hr_portal_backend.resultVO.LetterResponseDTO;
import com.gfss.hr_portal_backend.service.LetterService;
@Service
public class LetterServiceImpl implements LetterService
{
	private final LetterRepository letterRepository;
	
    private final EmployeeRepository employeeRepository;
    
    public LetterServiceImpl(LetterRepository letterRepository, EmployeeRepository employeeRepository) {
        this.letterRepository = letterRepository;
		this.employeeRepository = employeeRepository;
    }
    


    @Override
    public Letter saveLetter(String employeeName, String letterType, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is required");
        }

        // ✅ Check content type
        String contentType = file.getContentType();
        if (contentType == null || !contentType.equalsIgnoreCase("application/pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed");
        }

        // ✅ Optional: Additional filename check (for extra safety)
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("File must have a .pdf extension");}
        
        // Split name into first and last
        String[] names = employeeName.trim().split("\\s+", 2);
        if (names.length < 2) throw new IllegalArgumentException("Invalid employee name");

        String firstName = names[0];
        String lastName = names[1];

        // Find employee by first and last name
        EmployeeEntity employee = employeeRepository
            .findByFirstNameAndLastName(firstName, lastName)
            .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + employeeName));
        String empname=employee.getFirstName()+" "+employee.getLastName();
        // Create and save letter
        Letter letter = new Letter();
        letter.setEmpId(employee.getEmpId());
        letter.setEmpName(empname);
        letter.setLetterType(letterType);
        letter.setFileName(file.getOriginalFilename());
        letter.setUploadDate(LocalDate.now());
        letter.setFileData(file.getBytes());
       // Step 1: Insert to get MongoDB-generated _id
        letter = letterRepository.insert(letter);
 
        // Step 2: Convert ObjectId to numeric string
        letter.setLetterId(convertObjectIdToNumeric(letter.getId()));
        return letterRepository.save(letter);
    }
    
    @Override
    public Letter getLetterByLetterId(String letterId) {
        return letterRepository.findByLetterId(letterId);
    }
    
 // Converts MongoDB ObjectId (hex string) to numeric-only string
    private String convertObjectIdToNumeric(String objectId) {
        StringBuilder numericId = new StringBuilder();
        for (char ch : objectId.toCharArray()) {
            if (Character.isDigit(ch)) {
                numericId.append(ch);
            } else if (Character.isLetter(ch)) {
                numericId.append((int) ch);
            }
        }
        return numericId.toString();
    }


    


    @Override
    public List<Letter> getAllLetters() {
        return letterRepository.findAll();
    }


    @Override
    public Letter updateLetter(String id, String employeeName, String letterType, MultipartFile file) throws Exception {
        Letter existing = getLetterByLetterId(id);

//        existing.setEmployeeName(employeeName);
        existing.setLetterType(letterType);
        existing.setUploadDate(LocalDate.now());

        if (file != null) {
            if (!"application/pdf".equalsIgnoreCase(file.getContentType())) {
                throw new IllegalArgumentException("Only PDF files are allowed");
            }
            existing.setFileName(file.getOriginalFilename());
            existing.setFileData(file.getBytes());
        }

        return letterRepository.save(existing);
    }

    @Override
    public boolean deleteLetter(String id)  {
    	
    	if (letterRepository.existsByLetterId(id)) {

    		letterRepository.deleteByLetterId(id);

            return true;

        }

        return false;
    	

    }

    @Override
    public byte[] downloadFile(String id) throws Exception {
        Letter letter = getLetterByLetterId(id);
        return letter.getFileData();
    }
}
