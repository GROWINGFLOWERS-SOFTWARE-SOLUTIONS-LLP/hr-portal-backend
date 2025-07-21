package com.gfss.hr_portal_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;
import com.gfss.hr_portal_backend.repository.EmployeeRepository;
import com.gfss.hr_portal_backend.resultVO.EmployeeRequest;
import com.gfss.hr_portal_backend.resultVO.ProfileResponse;
import com.gfss.hr_portal_backend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
    @Override
    public EmployeeEntity addEmployee(EmployeeRequest request) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setDepartment(request.getDepartment());
        employee.setManager(request.getManager());
        employee.setMobile(request.getMobile());
        employee.setEmailId(request.getEmailId());
        employee.setPassword(request.getPassword());
        employee.setOldpassword(request.getOldpassword());
        employee.setRole(request.getRole());
        employee.setJoiningDate(request.getJoiningDate());
        employee.setAddress(request.getAddress());
        employee.setCreatedBy(request.getCreatedBy());
        employee.setModifiedBy(request.getModifiedBy());
        employee.setCreatedDate(LocalDateTime.now());
        employee.setModifiedDate(LocalDateTime.now());

        // Step 1: Insert to get MongoDB-generated _id
        employee = employeeRepository.insert(employee);

        // Step 2: Convert ObjectId to numeric string
        employee.setEmpId(convertObjectIdToNumeric(employee.getId()));

        // Step 3: Save again with custom empId
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeEntity updateEmployee(String empId, EmployeeRequest request) {
        EmployeeEntity employee = employeeRepository.findByEmpId(empId);
        if (employee == null) return null;

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setDepartment(request.getDepartment());
        employee.setManager(request.getManager());
        employee.setMobile(request.getMobile());
        employee.setEmailId(request.getEmailId());
        employee.setPassword(request.getPassword());
        employee.setOldpassword(request.getOldpassword());
        employee.setRole(request.getRole());
        employee.setJoiningDate(request.getJoiningDate());
        employee.setAddress(request.getAddress());
        employee.setModifiedBy(request.getModifiedBy());
        employee.setModifiedDate(LocalDateTime.now());

        return employeeRepository.save(employee);
    }

    @Override
    public boolean deleteEmployee(String empId) {
        if (employeeRepository.existsByEmpId(empId)) {
            employeeRepository.deleteByEmpId(empId);
            return true;
        }
        return false;
    }

    @Override
    public EmployeeEntity getEmployeeByEmpId(String empId) {
        return employeeRepository.findByEmpId(empId);
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
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
    public List<EmployeeEntity> getEmployeesByRole(String role) {
        return employeeRepository.findByRole(role);
    }
	
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
