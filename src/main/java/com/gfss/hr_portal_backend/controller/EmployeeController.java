package com.gfss.hr_portal_backend.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;
import com.gfss.hr_portal_backend.resultVO.ApiResponse;
import com.gfss.hr_portal_backend.resultVO.EmployeeRequest;
import com.gfss.hr_portal_backend.resultVO.ProfileResponse;
import com.gfss.hr_portal_backend.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employees")
	public ResponseEntity<ApiResponse<EmployeeEntity>> addEmployee(@Valid @RequestBody EmployeeRequest request) {
		EmployeeEntity employee = employeeService.addEmployee(request);
		return ResponseEntity.ok(new ApiResponse<>("success", "Employee added successfully", employee));
	}

	@PutMapping("/employees/{empId}")
	public ResponseEntity<ApiResponse<EmployeeEntity>> updateEmployee(
			@PathVariable String empId, @Valid @RequestBody EmployeeRequest request) {
		EmployeeEntity updated = employeeService.updateEmployee(empId, request);
		if (updated != null) {
			return ResponseEntity.ok(new ApiResponse<>("success", "Employee updated", updated));
		}
		return ResponseEntity.status(404).body(new ApiResponse<>("error", "Employee not found", null));
	}

	@DeleteMapping("/employees/{empId}")
	public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable String empId) {
		boolean deleted = employeeService.deleteEmployee(empId);
		if (deleted) {
			return ResponseEntity.ok(new ApiResponse<>("success", "Employee deleted", empId));
		}
		return ResponseEntity.status(404).body(new ApiResponse<>("error", "Employee not found", null));
	}

	@GetMapping("/employees/{empId}")
	public ResponseEntity<ApiResponse<EmployeeEntity>> getEmployeeById(@PathVariable String empId) {
		EmployeeEntity employee = employeeService.getEmployeeByEmpId(empId);
		if (employee != null) {
			return ResponseEntity.ok(new ApiResponse<>("success", "Employee found", employee));
		}
		return ResponseEntity.status(404).body(new ApiResponse<>("error", "Employee not found", null));
	}

	@GetMapping("/employees")
	public ResponseEntity<ApiResponse<List<EmployeeEntity>>> getAllEmployees() {
		return ResponseEntity.ok(new ApiResponse<>("success", "All employees fetched", employeeService.getAllEmployees()));
	}
	
	@GetMapping("/employees/hr")
	public ResponseEntity<ApiResponse<List<EmployeeEntity>>> getAllHRs() {
	    List<EmployeeEntity> hrs = employeeService.getEmployeesByRole("HR");
	    if (hrs.isEmpty()) {
	        return ResponseEntity.ok(new ApiResponse<>("success", "No HRs found", hrs));
	    }
	    return ResponseEntity.ok(new ApiResponse<>("success", "HRs fetched", hrs));
	}
	
	@GetMapping("/employees/profile")
	public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(@RequestParam String empId){
		ProfileResponse profile = employeeService.getMyProfile(empId);
		
		return ResponseEntity.ok(new ApiResponse<>("success", "Profile fetched", profile));
	}
}
