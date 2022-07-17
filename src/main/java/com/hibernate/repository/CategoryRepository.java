package com.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.entitiy.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer>{
	

}
