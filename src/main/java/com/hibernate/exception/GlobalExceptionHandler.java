package com.hibernate.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hibernate.utility.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	// this method will automatically run whenever we get AnkitException
	@ExceptionHandler(AnkitException.class)
	public ResponseEntity<?> handleAnkitException(AnkitException ex) {
		
		ApiResponse response = new ApiResponse(ex.getMessage(),ex.getStatus(),ex.getThrownByClassName(), ex.getThrownByMethodName());
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(BadApiRequest.class)
	public ResponseEntity<String> handleBadApiRequest(BadApiRequest ex) {
		
		String response = ex.getMessage();
		return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	// this method will automatically run whenever we get ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		
		Map<String,String> map = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach( (error) -> {
			
			String message = error.getDefaultMessage();
			String fieldName = ( (FieldError) error).getField();
			map.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(BadUserLoginDetailsException.class)
	public ResponseEntity<String> handleBadUserLoginDetailsException(BadUserLoginDetailsException ex) {
		
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
