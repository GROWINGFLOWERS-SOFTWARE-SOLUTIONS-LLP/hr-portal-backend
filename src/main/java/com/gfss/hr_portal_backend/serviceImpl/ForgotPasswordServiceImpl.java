package com.gfss.hr_portal_backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;
import com.gfss.hr_portal_backend.repository.EmployeeRepository;
import com.gfss.hr_portal_backend.resultVO.ForgotPasswordRequest;
import com.gfss.hr_portal_backend.service.ForgotPasswordService;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity changePassword(String email, ForgotPasswordRequest request) throws Exception {

        EmployeeEntity employee = employeeRepository.findByEmailId(email);

        if (employee == null) {
            throw new Exception("Employee not found with this email.");
        }

        if (!employee.getPassword().equals(request.getOldPassword())) {
            throw new Exception("Old password does not match.");
        }

        if (request.getOldPassword().equals(request.getNewPassword())) {
            throw new Exception("New password must be different from old password.");
        }

        if (!request.getNewPassword().equals(request.getRetypeNewPassword())) {
            throw new Exception("New password and retype new password do not match.");
        }

        employee.setOldpassword(employee.getPassword());
        employee.setPassword(request.getNewPassword());
        employee.setModifiedDate(java.time.LocalDateTime.now());

        return employeeRepository.save(employee);
    }
}
