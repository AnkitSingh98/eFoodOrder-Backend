package com.hibernate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.entitiy.Cart;
import com.hibernate.entitiy.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	 Optional<Cart> findByUser(User user);

}
