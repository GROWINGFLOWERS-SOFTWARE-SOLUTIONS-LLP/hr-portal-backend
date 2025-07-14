package com.gfss.hr_portal_backend.service;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;
import com.gfss.hr_portal_backend.resultVO.ForgotPasswordRequest;

public interface ForgotPasswordService {
    EmployeeEntity changePassword(String email, ForgotPasswordRequest request) throws Exception;
}
