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
import com.gfss.hr_portal_backend.utils.IdGenerateUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeEntity addEmployee(EmployeeRequest request) {
	    // 1. Validate role
	    if (!request.getRole().equalsIgnoreCase("Employee") &&
	        !request.getRole().equalsIgnoreCase("HR")) {
	        throw new IllegalArgumentException("Role must be either 'Employee' or 'HR'");
	    }

	    // 2. Check duplicate email
	    if (employeeRepository.findByEmailId(request.getEmailId()) != null) {
	        throw new IllegalArgumentException("Email already exists. Please use a different email.");
	    }

	    EmployeeEntity employee = new EmployeeEntity();
	    employee.setFirstName(request.getFirstName());
	    employee.setLastName(request.getLastName());
	    employee.setDepartment(request.getDepartment());
	    employee.setManager(request.getManager());
	    employee.setMobile(request.getMobile());
	    employee.setEmailId(request.getEmailId());
	    employee.setPassword(request.getPassword()); // 🔒 In real projects, hash this
	    employee.setOldpassword(request.getOldpassword());
	    employee.setRole(request.getRole());
	    employee.setJoiningDate(request.getJoiningDate());
	    employee.setAddress(request.getAddress());
	    employee.setCreatedBy(request.getCreatedBy());
	    employee.setModifiedBy(request.getModifiedBy());
	    employee.setCreatedDate(LocalDateTime.now());
	    employee.setModifiedDate(LocalDateTime.now());

	    // Insert to get MongoDB ID
	    employee = employeeRepository.insert(employee);

	    // Generate custom empId
	    employee.setEmpId(IdGenerateUtil.convertObjectIdToNumeric(employee.getId()));

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
	
	@Override
    public long countEmployees() {
        return employeeRepository.count();
    }
}
