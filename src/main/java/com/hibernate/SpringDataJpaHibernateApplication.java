package com.hibernate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hibernate.entitiy.Product;
import com.hibernate.entitiy.Role;
import com.hibernate.entitiy.User;
import com.hibernate.payload.RoleDto;
import com.hibernate.payload.UserDto;
import com.hibernate.repository.ProductRepository;
import com.hibernate.repository.RoleRepository;
import com.hibernate.repository.UserRepository;
import com.hibernate.services.UserService;


@SpringBootApplication
public class SpringDataJpaHibernateApplication implements CommandLineRunner{

	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringDataJpaHibernateApplication.class, args);
	}

    @Bean
    ModelMapper mapper() {
        return new ModelMapper();
    }

	
	
	@Override
	public void run(String... args) throws Exception {
		
		
		try {
		Role role1 = new Role();
		role1.setId(101);
		role1.setName("ROLE_ADMIN");
		
		Role role2 = new Role();
		role2.setId(102);
		role2.setName("ROLE_NORMAL");
		
		Role role3 = new Role();
		role3.setId(103);
		role3.setName("ROLE_STAFF");
		
		//roleRepository.saveAll(List.of(role1,role2,role3));
		roleRepository.saveAll(Arrays.asList(role1,role2,role3));
		
		Set<Role> roles = new HashSet<>();
		roles.add(role1);
		roles.add(role2);
		User user = new User(1, "Ankit", "ankit@gmail.com", this.passwordEncoder.encode("ankit"), roles);
		userRepository.save(user);
		
		}catch(Exception e) {
			System.out.println("Role already exists!!!");
		}
	}

}
