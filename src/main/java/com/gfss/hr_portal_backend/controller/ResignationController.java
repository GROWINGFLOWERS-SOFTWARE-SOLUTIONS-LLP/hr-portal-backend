package com.gfss.hr_portal_backend.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;
import com.gfss.hr_portal_backend.entity.Resignation;
import com.gfss.hr_portal_backend.repository.EmployeeRepository;
import com.gfss.hr_portal_backend.resultVO.ApiResponse;
import com.gfss.hr_portal_backend.resultVO.EmployeeRequest;
import com.gfss.hr_portal_backend.resultVO.ResignationRequest;
import com.gfss.hr_portal_backend.service.ResignationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resignations")
public class ResignationController 
{
	 private final ResignationService resignationService;
	 
	 @Autowired
	 private EmployeeRepository employeeRepository;

	    public ResignationController(ResignationService resignationService) {
	        this.resignationService = resignationService;
	    }

//	    @PostMapping
//	    public ResponseEntity<?> createResignation(@RequestBody Resignation resignation) {
//	        try {
//	            Resignation saved = resignationService.saveResignation(resignation);
//	            return ResponseEntity.ok(saved);
//	        } catch (IllegalArgumentException e) {
//	            return ResponseEntity.badRequest().body(e.getMessage());
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating resignation");
//	        }
//	    }

//	    @PostMapping
//	    public ResponseEntity<?> createResignation(@RequestBody ResignationRequest resignation) {
//	    	
//	    	System.out.println(resignation);
//	        try {
//	        	
//	            Resignation saved = resignationService.saveResignation(resignation);
//	            System.out.println("Saved"+saved);
//	            return ResponseEntity.ok(saved);
//	        } catch (IllegalArgumentException e) {
//	            return ResponseEntity.badRequest().body(e.getMessage());
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating resignation");
//	        }
//	    }
	    
	    @PostMapping
	    public  ResponseEntity<ApiResponse<Resignation>> createResignation(@RequestBody ResignationRequest resignationRequest)
	    {
	     
	    	String[] names = resignationRequest.getEmpName().trim().split("\\s+", 2);

	        if (names.length < 2) {
	            return ResponseEntity.badRequest().body(
	                new ApiResponse<>("error", "Invalid employee name format. Expected: 'FirstName LastName'", null)
	            );
	        }

	        String firstName = names[0];
	        String lastName = names[1];

	        Optional<EmployeeEntity> optionalEmployee = employeeRepository
	            .findByFirstNameAndLastName(firstName, lastName);

	        if (optionalEmployee.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	                new ApiResponse<>("error", "Employee not found: " + resignationRequest.getEmpName(), null)
	            );
	        }

	        // Proceed to save resignation
	        Resignation resignation = resignationService.saveResignation(resignationRequest);
	        return ResponseEntity.ok(new ApiResponse<>("success", "Resignation added successfully", resignation));
	    }
	    	
	    
	   

	    
	    @GetMapping
	    public ResponseEntity<ApiResponse<List<Resignation>>> getAllResignations() {

	        return ResponseEntity.ok(new ApiResponse<>("success", "All resignations fetched", resignationService.getAllResignations()));
	    }
	    
	    @GetMapping("/{regId}")

		public ResponseEntity<ApiResponse<Resignation>> getResignationByregId(@PathVariable String regId) 
	    {
            Resignation resignation=resignationService.getResignationByRegId(regId);

			if (resignation != null) {

				return ResponseEntity.ok(new ApiResponse<>("success", "Resignation found", resignation));

			}

			return ResponseEntity.status(404).body(new ApiResponse<>("error", "Resignation not found", null));

		}


//	    @GetMapping("/{id}")
//	    public ResponseEntity<?> getResignationById(@PathVariable String id) {
//	        try {
//	            Resignation resignation = resignationService.getResignationById(id)
//	                    .orElseThrow(() -> new IllegalArgumentException("Resignation not found with id: " + id));
//	            return ResponseEntity.ok(resignation);
//	        } catch (IllegalArgumentException e) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//	        }
//	    }
	    
//	    @PutMapping("/{id}/status")
//	    public ResponseEntity<Resignation> updateResignationStatus(
//	        @PathVariable String id,
//	        @RequestBody Map<String, String> payload
//	    ) {
//	        String statusValue = payload.get("status");
//	        Resignation.Status status;
//
//	        try {
//	            status = Resignation.Status.valueOf(statusValue);
//	        } catch (IllegalArgumentException ex) {
//	            return ResponseEntity.badRequest().build(); // invalid status
//	        }
//
//	        Resignation updated = resignationService.findByIdAndStatus(id, status);
//	        return ResponseEntity.ok(updated);
//	    }
	    
//	    @PutMapping("/{id}")
//	    public ResponseEntity<?> updateResignation(@PathVariable String id, @RequestBody Resignation resignation) {
//	        try {
//	            Resignation updated = resignationService.updateResignation(id, resignation);
//	            return ResponseEntity.ok(updated);
//	        } catch (IllegalArgumentException e) {
//	            return ResponseEntity.badRequest().body(e.getMessage());
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resignation not found");
//	        }
//	    }

	    
	    
	    
//	    @DeleteMapping("/{regid}")
//	    public ResponseEntity<?> deleteResignation(@PathVariable String regid) {
//	        try {
//	            resignationService.deleteResignation(regid);
//	            return ResponseEntity.ok("Resignation deleted successfully");
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resignation not found");
//	        }
//	    }
	    
	    
	    @PutMapping("/{regId}")
	    public ResponseEntity<ApiResponse<Resignation>> updateEmployee(

				@PathVariable String regId, @Valid @RequestBody ResignationRequest request) {

			Resignation updated = resignationService.updateResignation(regId, request);

			if (updated != null) {

				return ResponseEntity.ok(new ApiResponse<>("success", "Resignation updated for Employee", updated));

			}

			return ResponseEntity.status(404).body(new ApiResponse<>("error", "Resignation for Employee not found", null));

		}
	    
	    @DeleteMapping("/{regId}")
	    public ResponseEntity<ApiResponse<String>> deleteResignation(@PathVariable String regId) {

			boolean deleted = resignationService.deleteResignation(regId);

			if (deleted) {

				return ResponseEntity.ok(new ApiResponse<>("success", "Resignation deleted successfully", regId));

			}

			return ResponseEntity.status(404).body(new ApiResponse<>("error", "Resignation not found", null));

		}

	    @GetMapping("/search")
	    public ResponseEntity<ApiResponse<List<Resignation>>> searchByEmployeeName(@RequestParam String empName) {
	        List<Resignation> resignations = resignationService.findByEmpNameContainingIgnoreCase(empName);
	        if (resignations != null && !resignations.isEmpty()) {

				return ResponseEntity.ok(new ApiResponse<>("success", "Resignation with EmployeeName found", resignations));

			}

			return ResponseEntity.status(404).body(new ApiResponse<>("error", "Resignations with Employee not found", null));
	        
	    }
}
