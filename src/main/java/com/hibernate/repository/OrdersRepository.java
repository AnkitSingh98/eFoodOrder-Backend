package com.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.entitiy.Order;

public interface OrdersRepository extends JpaRepository<Order, Integer> {

}
