package com.hibernate.services.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hibernate.entitiy.Role;
import com.hibernate.entitiy.User;
import com.hibernate.exception.AnkitException;
import com.hibernate.exception.ResourceNotFoundException;
import com.hibernate.payload.CartDto;
import com.hibernate.payload.UserDto;
import com.hibernate.repository.RoleRepository;
import com.hibernate.repository.UserRepository;
import com.hibernate.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${user.profile.image.path}")
	private String imagePath;

	// Create User
	@Override
	public UserDto create(UserDto userDto) {
		
		User user = this.toEntity(userDto);

		Role role = this.roleRepository.findById(102).get();
		user.getRoles().add(role);

		User createdUser = this.userRepository.save(user);
		return this.toDto(createdUser);

	}

	@Override
	public UserDto update(UserDto t, int userId) {
		User u = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(" User not found " + userId));

		u.setName(t.getName());
		u.setEmail(t.getEmail());
		
		if( t.getPassword() != null &&  t.getPassword() != ""  &&  !(t.getPassword().equalsIgnoreCase(u.getPassword())) )
		u.setPassword(this.passwordEncoder.encode(t.getPassword()));    // u.setPassword(t.getPassword());
		
		u.setAbout(t.getAbout());
		u.setAddress(t.getAddress());
		u.setActive(t.isActive());
		u.setGender(t.getGender());
		u.setCreateAt(t.getCreateAt());
		u.setPhone(t.getPhone());
		u.setImageName(t.getImageName());

		User updatedUser = this.userRepository.save(u);

		return this.toDto(updatedUser);
	}

	@Override
	public void delete(int userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(" User Not Found " + userId));
		
		String fullPath = imagePath + user.getImageName();
		
		try {
			Path path = Paths.get(fullPath);
			Files.delete(path);
		}catch(NoSuchFileException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		this.userRepository.delete(user);

	}

	@Override
	public List<UserDto> getAll() {

		List<User> allUser = this.userRepository.findAll();
		List<UserDto> allUserDto = allUser.stream().map(user -> this.toDto(user)).collect(Collectors.toList());

		return allUserDto;
	}

	@Override
	public UserDto getByUserId(int userId) throws AnkitException {

		User u = this.userRepository.findById(userId)
				.orElseThrow(() -> new AnkitException("User not Found with userId: " + userId, HttpStatus.NOT_FOUND, "UserServiceImpl", "getByUserId"));

		return this.toDto(u);
	}

	@Override
	public UserDto getByEmail(String email) {

		User u = this.userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException());

		return this.mapper.map(u, UserDto.class);
	}

	// Convert User to UserDto
	public UserDto toDto(User user) {

		return this.mapper.map(user, UserDto.class);

	}

	// Convert UserDto to User Entity
	public User toEntity(UserDto t) {

		return this.mapper.map(t, User.class);

	}

}
