package com.gfss.hr_portal_backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;
import com.gfss.hr_portal_backend.repository.EmployeeRepository;
import com.gfss.hr_portal_backend.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeEntity login(String email, String password) {
	    EmployeeEntity employee = employeeRepository.findByEmailId(email);
	    if (employee != null && employee.getPassword().equals(password)) {
	        return employee;
	    }
	    return null;
	}
	
}