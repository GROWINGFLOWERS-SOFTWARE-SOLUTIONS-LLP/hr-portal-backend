package com.gfss.hr_portal_backend.controller;

import java.util.List;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.gfss.hr_portal_backend.entity.Letter;
import com.gfss.hr_portal_backend.resultVO.ApiResponse;
import com.gfss.hr_portal_backend.resultVO.UploadLetterRequest;
import com.gfss.hr_portal_backend.service.LetterService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/letters")
public class LetterController 
{


   	private final LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }
    
    
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadLetter(@ModelAttribute UploadLetterRequest request) {
    	System.out.println("In Control"+request);
        try {
            Letter saved = letterService.saveLetter(request.getEmployeeName(), request.getLetterType(), request.getFile());
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }



    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Letter>>> getAllLetters() {

        return ResponseEntity.ok(new ApiResponse<>("success", "All Letters fetched",letterService.getAllLetters()));
    }


    
    
    
    @PutMapping(value = "/{letterId}", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Letter>> updateLetter(
    		@PathVariable String letterId,@RequestParam String employeeName,
            @RequestParam String letterType,
            @RequestParam(required = false) MultipartFile file ) throws Exception {

		Letter updated = letterService.updateLetter(letterId,employeeName,letterType,file);
		System.out.printf("Control",updated);

		if (updated != null) {

			return ResponseEntity.ok(new ApiResponse<>("success", "Letter updated for Employee", updated));

		}

		return ResponseEntity.status(404).body(new ApiResponse<>("error", "Letter for Employee not found", null));

	}

    @GetMapping("/{letterId}")
	public ResponseEntity<ApiResponse<Letter>> getEmployeeById(@PathVariable String letterId) throws Exception {
		Letter letter = letterService.getLetterByLetterId(letterId);
		System.out.println(letter);
		if (letter != null) {
			return ResponseEntity.ok(new ApiResponse<>("success", "Letter found", letter));
		}
		return ResponseEntity.status(404).body(new ApiResponse<>("error", "Letter not found", null));
	}
    
    @DeleteMapping("/{letterId}")

	public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable String letterId) {

		boolean deleted = letterService.deleteLetter(letterId);

		if (deleted) {

			return ResponseEntity.ok(new ApiResponse<>("success", "Employee deleted", letterId));

		}

		return ResponseEntity.status(404).body(new ApiResponse<>("error", "Employee not found", null));

	}
    
 
    

    // Download PDF file
    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable String id) {
        try {
            byte[] fileData = letterService.downloadFile(id);
            Letter letter = letterService.getLetterByLetterId(id);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + letter.getFileName() + "\"")
                    .body(fileData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }

	
	
}
