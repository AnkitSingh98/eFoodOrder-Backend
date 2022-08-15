package com.hibernate.controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.entitiy.Role;
import com.hibernate.payload.RoleDto;
import com.hibernate.payload.UserDto;
import com.hibernate.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	ModelMapper mapper;
	
	//Create User
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
		
		userDto.setCreateAt(new Date().toString());
		userDto.setActive(true);
		
		userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		UserDto createdUser = this.userService.create(userDto);
		return new ResponseEntity<UserDto>(createdUser,HttpStatus.CREATED);
	}
	
	
	//Update User
	@PutMapping("/user/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto toUpdate,@PathVariable int userId) {
		
		UserDto updatedUser = userService.update(toUpdate, userId);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
		
	}
	
	//Delete User
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId) {
		
		userService.delete(userId);
		return new ResponseEntity<String>("User is deleted successfully !!", HttpStatus.OK);
		
	}
	
	
	
	//Get All
	@GetMapping("/user")
	public ResponseEntity<List<UserDto>> getAll(){
	
		List<UserDto> allUsers = userService.getAll();
		return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
	}
	
	
	//GetById
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDto> getByUserId(@PathVariable int userId) {
		
		UserDto u = userService.getByUserId(userId);
		return new ResponseEntity<UserDto>(u,HttpStatus.OK);
	}
	
	//GetByEmail
	@GetMapping("/user/email/{email}")
	public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
		
		UserDto u = userService.getByEmail(email);
		return new ResponseEntity<UserDto>(u,HttpStatus.OK);
		
	}
	
	

}
