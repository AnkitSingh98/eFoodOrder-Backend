package com.hibernate.payload;






// This class is a model for our token which we will 
// send when any token generation request comes

public class JwtResponse {
	
	private String token;
	private UserDto user;
	
	
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public JwtResponse(String token, UserDto user) {
		super();
		this.token = token;
		this.user = user;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public UserDto getUser() {
		return user;
	}


	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
	
	
	

}
