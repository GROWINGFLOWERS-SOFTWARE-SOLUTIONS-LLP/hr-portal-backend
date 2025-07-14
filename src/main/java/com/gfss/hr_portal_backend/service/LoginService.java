package com.gfss.hr_portal_backend.service;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;

public interface LoginService {

	EmployeeEntity login(String emailId);
}
