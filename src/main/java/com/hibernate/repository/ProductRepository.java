package com.hibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernate.entitiy.Category;
import com.hibernate.entitiy.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
	
	List<Product> findByCategory(Category category);
	
	

}
