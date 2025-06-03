package com.gfss.hr_portal_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfss.hr_portal_backend.entity.Login;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	//Dummy Credentials
	private final String DUMMY_USERNAME = "testuser";
	private final String DUMMY_PASSWORD = "testpass";
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody Login login){
	    if(DUMMY_USERNAME.equals(login.getUsername()) && DUMMY_PASSWORD.equals(login.getPassword())) {
	        return new ResponseEntity<>("Login Successful", HttpStatus.ACCEPTED);
	    } else {
	        return new ResponseEntity<>("Invalid Username or Password", HttpStatus.UNAUTHORIZED);
	    }
	}
}