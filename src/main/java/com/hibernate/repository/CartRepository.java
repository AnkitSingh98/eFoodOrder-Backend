package com.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.entitiy.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
