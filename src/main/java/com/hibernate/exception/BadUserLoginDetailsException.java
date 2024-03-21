package com.hibernate.exception;

public class BadUserLoginDetailsException extends RuntimeException{
	
	public BadUserLoginDetailsException() {
		
		super("Invalid Username or Password !!!");
	}
	
	public BadUserLoginDetailsException(String message) {
		super(message);
	}

}
