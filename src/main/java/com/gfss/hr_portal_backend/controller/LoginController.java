package com.gfss.hr_portal_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;
import com.gfss.hr_portal_backend.resultVO.ApiResponse;
import com.gfss.hr_portal_backend.resultVO.LoginRequest;
import com.gfss.hr_portal_backend.service.LoginService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@Valid @RequestBody LoginRequest login) {
        try {
            EmployeeEntity employee = loginService.login(login.getEmail());

            if (employee == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse<>("error", "Invalid Email ID", null));
            }

            if (!employee.getPassword().equals(login.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse<>("error", "Invalid Password", null));
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>("success", "Login Successful", employee));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("error", "An error occurred: " + e.getMessage(), null));
        }
    }
}
