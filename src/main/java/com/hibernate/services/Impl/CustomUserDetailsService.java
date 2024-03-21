package com.hibernate.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hibernate.entitiy.User;
import com.hibernate.exception.ResourceNotFoundException;
import com.hibernate.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UserRepository userRepository;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("------Here!!!!!!!!!!");
		
		User user = this.userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException());
		
		System.out.println("------Here2222222222!!!!!!!!!!");
		return user;
	}

}
