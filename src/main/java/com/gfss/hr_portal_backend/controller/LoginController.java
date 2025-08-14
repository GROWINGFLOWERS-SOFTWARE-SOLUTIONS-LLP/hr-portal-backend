package com.gfss.hr_portal_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gfss.hr_portal_backend.entity.EmployeeEntity;
import com.gfss.hr_portal_backend.resultVO.ApiResponse;
import com.gfss.hr_portal_backend.resultVO.LoginRequest;
import com.gfss.hr_portal_backend.service.LoginService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<EmployeeEntity>> login(@RequestBody LoginRequest request) {
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());

        EmployeeEntity emp = loginService.login(request.getEmail(), request.getPassword());

        if (emp != null) {
            return ResponseEntity.ok(
                new ApiResponse<>("success", "Login successful", emp)
            );
        }

        return ResponseEntity.status(401).body(
            new ApiResponse<>("error", "Invalid email or password", null)
        );
    }

}
