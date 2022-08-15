package com.hibernate.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	
	//Logger is used to print to console
	Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
			
			
	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		
		/* When user hits api with token 
		 * then before hitting Controller class this class and method it will hit
		 * and authentication will be done in below steps
		 * a) Get token from request
		 * b) Get Username from token
		 * c) Get corresponding user from database
		 * d) Validate token
		 * e) Set auth to spring security
		 *  Based on the auth -- Spring Security allows access
		 *  
		 * */
		
		
		// The request made by user comes to backend through HttpServlet: So here we are using the concept of HttpServlet
		// user hits api with ( header + url )... header has the token
		// token is in the form of key value pair ie    Authorization = Bearer asdf12.klmn198.qrst67
		String requestToken = request.getHeader("Authorization");
		
		
		//To print token to console
		logger.info("message {}", requestToken);
		
		String username = null;
		String token = null;
		
		if(requestToken != null && requestToken.trim().startsWith("Bearer")) {
			
			
			//Our actual token starts after bearer
			token = requestToken.substring(7);
			
			// get username from token
			try {
				
				username = this.helper.getUsernameFromToken(token);
				
			}catch(ExpiredJwtException e) {
				logger.info("Invalid token message {}", "Jwt Token Expired!!");
			}catch(MalformedJwtException e) {
				logger.info("Invalid token message {}", "Invalid Jwt Token!!");
			}catch(IllegalArgumentException e) {
				logger.info("Invalid token message {}", "Unable to get token");
			}
			
			
			
			// get UserDetails and then Validate token
			//check if Spring Security should not be configured for some other authentication already
			
			if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				
				// Get user from database
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				
				
				
				//validate token ( if credentials of token match credentials of userDetails coming from db then valid)
				
				if(this.helper.validateToken(token, userDetails)) {
					
					// If token is valid Set auth to Spring Security ie configure it for our authentication
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);
					
				}
				else {
					logger.info("Invalid message {}", "Invalid Jwt token");
				}
				
				
				
			}else {
				logger.info("message {}","Username is null or auth is already there"); 
			}
			
			
			
		}else {
			logger.info("message {}","token does not starts with bearer");
		}
		
		
		//forward this request -- filterChain is coming from HttpServlet
		filterChain.doFilter(request, response);
		
		
	}
	
	

}
