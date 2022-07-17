package com.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaHibernateApplication {

	public static void main(String[] args) {
		System.out.println("Application started.....");
		SpringApplication.run(SpringDataJpaHibernateApplication.class, args);
	}

}
