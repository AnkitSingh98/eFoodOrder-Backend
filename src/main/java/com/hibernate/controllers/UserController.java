package com.hibernate.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hibernate.entitiy.Role;
import com.hibernate.exception.AnkitException;
import com.hibernate.payload.RoleDto;
import com.hibernate.payload.UserDto;
import com.hibernate.services.FileUploadService;
import com.hibernate.services.UserService;
import com.hibernate.utility.ImageResponse;



@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	FileUploadService fileUploadService;
	
	@Value("${user.profile.image.path}")
	private String imageUploadPath;
	
	//Create User
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
		
		System.out.println("Inside controller");
//		try {
//			//Thread.sleep(3000);
//		} catch (InterruptedException e) { 
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
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
	public ResponseEntity<UserDto> getByUserId(@PathVariable int userId) throws AnkitException{
		
		UserDto u = userService.getByUserId(userId);
		return new ResponseEntity<UserDto>(u,HttpStatus.OK);
	}
	
	//GetByEmail
	@GetMapping("/user/email/{email}")
	public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
		
		UserDto u = userService.getByEmail(email);
		return new ResponseEntity<UserDto>(u,HttpStatus.OK);
		
	}
	
	
	// upload user image
	
	@PostMapping("/image/{userId}")
	public ResponseEntity<ImageResponse> uploadUserImage( @RequestParam("userImage")MultipartFile image, @PathVariable int userId) throws IOException{
		
		UserDto useDto = userService.getByUserId(userId);
		
		String imageName = fileUploadService.uploadFile(image, imageUploadPath);
		
		useDto.setImageName(imageName);
		UserDto response = userService.update(useDto, userId);
		
		ImageResponse imageResponse = new ImageResponse(imageName, "Image created successfully", true, HttpStatus.CREATED);
		return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
		
	}
	
	
	
	// serve user image
	
	@GetMapping("/image/{userId}")
	public void serveUserImage(@PathVariable int userId, HttpServletResponse response) throws IOException {
		
		UserDto userDto = userService.getByUserId(userId);
		InputStream resource = fileUploadService.getResource(imageUploadPath, userDto.getImageName());
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	

}
