package com.gfss.hr_portal_backend.service;

import com.gfss.hr_portal_backend.resultVO.ProfileResponse;

public interface EmployeeService {

	ProfileResponse getMyProfile(String empId);
	
}
