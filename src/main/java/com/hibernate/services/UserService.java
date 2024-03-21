package com.hibernate.services;

import java.util.List;

import com.hibernate.exception.AnkitException;
import com.hibernate.payload.UserDto;

public interface UserService {
	
	//Create
	
	UserDto create(UserDto userDto);
	
	//update
	UserDto update(UserDto userDto, int userId);
	
	//delete
	void delete(int userId);
	
	//get All Users
	List<UserDto> getAll();
	
	//get by id
	UserDto getByUserId(int userId) throws AnkitException;
	
	//get By email
	UserDto getByEmail(String email);

}
