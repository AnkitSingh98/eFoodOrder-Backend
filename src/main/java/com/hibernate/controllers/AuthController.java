package com.hibernate.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.exception.BadUserLoginDetailsException;
import com.hibernate.payload.JwtRequest;
import com.hibernate.payload.JwtResponse;
import com.hibernate.payload.UserDto;
import com.hibernate.security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping("/login")
	public JwtResponse login( @RequestBody JwtRequest request) throws Exception {
		
		
		// authenticate
		// if user is valid this method runs successfully and program continues  else it will throw exception
		this.authenticateUser(request.getUsername(),request.getPassword());
		
		// get UserDetails
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		// get token
		String token = this.helper.generateToken(userDetails);
		
		// Send Response
		JwtResponse response = new JwtResponse();
		response.setToken(token);
		response.setUser(this.mapper.map(userDetails, UserDto.class));
		return response;
	}
	
	
	
	
	
	public void authenticateUser(String username, String password) throws Exception {
		
		try {
			
			
		// authenticate
		// this method will take username and password and authenticate from database that this user is present or not (as this method has UserDetails)
		// if user valid then this method executes successfully and program continues else it will throw exception and stops execution	
		manager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		
		}catch(BadCredentialsException e) {
			throw new BadUserLoginDetailsException("Invalid Username Password");
		}catch(DisabledException e) {
			throw new BadUserLoginDetailsException("User is not active");
		}
		
	}

}
