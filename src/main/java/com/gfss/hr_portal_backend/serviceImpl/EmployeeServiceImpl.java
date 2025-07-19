package com.gfss.hr_portal_backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;
import com.gfss.hr_portal_backend.repository.EmployeeRepository;
import com.gfss.hr_portal_backend.resultVO.ProfileResponse;
import com.gfss.hr_portal_backend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public ProfileResponse getMyProfile(String empId) {
		EmployeeEntity emp = employeeRepository.findByEmpId(empId);
		
		return new ProfileResponse(
				emp.getEmpId(),
				emp.getFirstName(),
				emp.getLastName(),
				emp.getDepartment(),
				emp.getRole(),
				emp.getMobile(),
				emp.getEmailId(),
				emp.getJoiningDate(),
				emp.getAddress()
				);
	}

}
