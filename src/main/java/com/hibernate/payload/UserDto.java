package com.hibernate.payload;

import java.math.BigInteger;
import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hibernate.entitiy.Role;


//@JsonInclude(Include.NON_NULL)
public class UserDto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@Column(nullable = false)
	@NotEmpty
	private String name;
	
	@Column(unique = true)
	@Email(message= "Valid email address is required !!")
	private String email;
	
//	@NotEmpty
//	@Size(min = 4, message = "Password must be of 4 digits!!")
	private String password;
	
	private String address;
	
	private String about;
	
	private String gender;
	
	private String imageName;

	private String createAt;
	
	private String phone;
	
	private boolean active;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	
	
	

	public UserDto(int userId, @NotEmpty String name, @Email(message = "Valid email address is required !!") String email, String password, Set<RoleDto> roles) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
	

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	//@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	
	
	

}
