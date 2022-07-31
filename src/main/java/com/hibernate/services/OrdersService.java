package com.hibernate.services;

import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

import com.hibernate.payload.OrderRequest;
import com.hibernate.payload.OrdersDto;

public interface OrdersService {

	
	// create order

	OrdersDto createOrder(OrderRequest request, String username);

	// update order
	OrdersDto updateOrder(OrdersDto orderDto, int orderId);

	// delete order
	void deleteOrder(int orderId);

	// get all orders
	List<OrdersDto> getAll();

	// get single order
	OrdersDto get(int orderId);
}
