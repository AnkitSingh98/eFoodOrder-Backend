package com.hibernate;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hibernate.entitiy.Product;
import com.hibernate.entitiy.Role;
import com.hibernate.repository.ProductRepository;
import com.hibernate.repository.RoleRepository;

@SpringBootApplication
public class SpringDataJpaHibernateApplication implements CommandLineRunner{

	
	@Autowired
	private RoleRepository roleRepository;
	
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringDataJpaHibernateApplication.class, args);
	}
	
	@Bean
	public  ModelMapper mapper() {
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
		
		roleRepository.saveAll(List.of(role1,role2,role3));
		
		}catch(Exception e) {
			System.out.println("Role already exists!!!");
		}
	}

}
