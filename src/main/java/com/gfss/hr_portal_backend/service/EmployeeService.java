package com.gfss.hr_portal_backend.service;

import java.util.List;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;
import com.gfss.hr_portal_backend.resultVO.EmployeeRequest;
import com.gfss.hr_portal_backend.resultVO.ProfileResponse;

public interface EmployeeService {

	EmployeeEntity addEmployee(EmployeeRequest request);
	EmployeeEntity updateEmployee(String empId, EmployeeRequest request);
	boolean deleteEmployee(String empId);
	EmployeeEntity getEmployeeByEmpId(String empId);
	List<EmployeeEntity> getAllEmployees();
	
	List<EmployeeEntity> getEmployeesByRole(String role);
	
	
	ProfileResponse getMyProfile(String empId);
	
}
