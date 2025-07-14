package com.gfss.hr_portal_backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;
import com.gfss.hr_portal_backend.entity.Resignation;
import com.gfss.hr_portal_backend.repository.EmployeeRepository;
import com.gfss.hr_portal_backend.repository.ResignationRepository;
import com.gfss.hr_portal_backend.resultVO.ResignationRequest;
import com.gfss.hr_portal_backend.service.ResignationService;

@Service
public class ResignationServiceImpl implements ResignationService 
{
	private final ResignationRepository resignationRepository;
	
	@Autowired
	private EmployeeRepository emprepository;

    public ResignationServiceImpl(ResignationRepository resignationRepository) {
        this.resignationRepository = resignationRepository;
    }

    @Override
    public Resignation saveResignation(ResignationRequest resignation) 
    {
    	System.out.printf("In service",resignation);
    	
    	String[] names = resignation.getEmpName().trim().split("\\s+", 2);
        if (names.length < 2) throw new IllegalArgumentException("Invalid employee name");

        String firstName = names[0];
        String lastName = names[1];

        // Find employee by first and last name
        EmployeeEntity employee = emprepository
            .findByFirstNameAndLastName(firstName, lastName)
            .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + resignation.getEmpName()));
        System.out.println("Employee"+employee);
        String empname=employee.getFirstName()+" "+employee.getLastName();
        Resignation resignation1=new Resignation();
        resignation1.setEmpName(empname);
        resignation1.setResignDate(resignation.getResignDate());
        resignation1.setLastWorkingDate(resignation.getLastWorkingDate());
        resignation1.setReason(resignation.getReason());
        resignation1.setStatus("Pending");
        resignation1.setNoticePeriodDays(30);
        resignation1=resignationRepository.insert(resignation1);
        
     // Step 2: Convert ObjectId to numeric string
        resignation1.setRegId(convertObjectIdToNumeric(resignation1.getId()));
        
        return resignationRepository.save(resignation1);
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

    public Resignation getResignationByRegId(String regId) {

        return resignationRepository.findByRegId(regId);

    }
    
    @Override
    public List<Resignation> getAllResignations() {
        return resignationRepository.findAll();
    }

    @Override
    public Optional<Resignation> getResignationById(String id) {
        return resignationRepository.findById(id);
    }

//    @Override
//    public Resignation updateResignation(String regid, ResignationRequest resignation) {
//        Resignation existing = resignationRepository.findByRegId(regid);
//        if (existing.isEmpty()) {
//            throw new IllegalArgumentException("Resignation not found with id: " + id);
//        }
//        resignation.setId(id);
//        return resignationRepository.save(resignation);
//    }

//    @Override
//    public Resignation findByIdAndStatus(String id, Resignation.Status status) {
//        Resignation resignation = resignationRepository.findById(id)
//            .orElseThrow(() -> new IllegalArgumentException("Resignation not found with id: " + id));
//
//        resignation.setStatus(status);
//        return resignationRepository.save(resignation);
//    }
//    @Override
//    public void deleteResignation(String id) {
//        Optional<Resignation> existing = resignationRepository.findById(id);
//        if (existing.isEmpty()) {
//            throw new IllegalArgumentException("Resignation not found with id: " + id);
//        }
//        resignationRepository.deleteById(id);
//    }
    
    
    @Override
    public Resignation updateResignation(String regid, ResignationRequest resignation) {
     Resignation existing = resignationRepository.findByRegId(regid);
     if(existing==null)
        return null;
     existing.setEmpName(resignation.getEmpName());
     existing.setResignDate(resignation.getResignDate());
     existing.setLastWorkingDate(resignation.getLastWorkingDate());
     existing.setNoticePeriodDays(30);
     existing.setReason(resignation.getReason());
     return resignationRepository.save(existing);
    }
    
    @Override
    public boolean deleteResignation(String regId) {

        if (resignationRepository.existsByRegId(regId)) {

        	resignationRepository.deleteByRegId(regId);

            return true;

        }

        return false;

    }

    @Override
    public List<Resignation> findByEmpNameContainingIgnoreCase(String empName) {
        return resignationRepository.findByEmpNameContainingIgnoreCase(empName);
    }
}
