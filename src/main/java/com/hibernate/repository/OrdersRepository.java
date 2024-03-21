package com.hibernate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.entitiy.Order;
import com.hibernate.entitiy.User;

public interface OrdersRepository extends JpaRepository<Order, Integer> {
	
	Optional<List<Order>> findByUser(User user);

}
