package com.gfss.hr_portal_backend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex){
		
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors().forEach(error -> 
			errors.put(error.getField(), error.getDefaultMessage())
				);
		
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
	}
	
}
