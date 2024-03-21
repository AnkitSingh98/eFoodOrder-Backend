package com.hibernate.utility;

import org.springframework.http.HttpStatus;

public class ApiResponse {
	
	String message;
	HttpStatus status;
	String thrownByClassName;
	String thrownByMethodName;
	
	
	public ApiResponse(String message, HttpStatus status, String thrownByClassName, String thrownByMethodName) {
		super();
		this.message = message;
		this.status = status;
		this.thrownByClassName = thrownByClassName;
		this.thrownByMethodName = thrownByMethodName;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public String getThrownByClassName() {
		return thrownByClassName;
	}


	public void setThrownByClassName(String thrownByClassName) {
		this.thrownByClassName = thrownByClassName;
	}


	public String getThrownByMethodName() {
		return thrownByMethodName;
	}


	public void setThrownByMethodName(String thrownByMethodName) {
		this.thrownByMethodName = thrownByMethodName;
	}
	
	
	
	

}
