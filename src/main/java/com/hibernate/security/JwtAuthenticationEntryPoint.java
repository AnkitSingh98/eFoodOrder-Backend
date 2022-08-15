package com.hibernate.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		// This class and method will trigger whenever we get unauthentication exception from client
		// Use response variable to set response to be sent to client 
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
		// To send a particular message to client in case of unauthentication
		PrintWriter writer = response.getWriter();
		writer.println("Unauthorized Access");
		
		
	}
	

}
