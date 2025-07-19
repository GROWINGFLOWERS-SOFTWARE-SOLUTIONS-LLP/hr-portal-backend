package com.gfss.hr_portal_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gfss.hr_portal_backend.resultVO.ApiResponse;
import com.gfss.hr_portal_backend.resultVO.ProfileResponse;
import com.gfss.hr_portal_backend.service.EmployeeService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees/profile")
	public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(@RequestParam String empId){
		ProfileResponse profile = employeeService.getMyProfile(empId);
		
		return ResponseEntity.ok(new ApiResponse<>("success", "Profile fetched", profile));
	}
}
