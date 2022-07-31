package com.hibernate;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hibernate.entitiy.Product;
import com.hibernate.repository.ProductRepository;

@SpringBootApplication
public class SpringDataJpaHibernateApplication {

	
	
	
	
	public static void main(String[] args) {
		System.out.println("Application started.....");
		
		SpringApplication.run(SpringDataJpaHibernateApplication.class, args);
	}
	
	@Bean
	public  ModelMapper mapper() {
		return new ModelMapper();
	}

}
