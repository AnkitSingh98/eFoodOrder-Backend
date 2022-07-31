package com.hibernate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.payload.OrderRequest;
import com.hibernate.payload.OrdersDto;
import com.hibernate.services.OrdersService;


@RestController
@RequestMapping("/orders")
public class OrderController {
	
	String userName = "ank99@gmail.com";

	@Autowired
	private OrdersService orderService;

	@PostMapping("/")
	public ResponseEntity<OrdersDto> createOrder(@RequestBody OrderRequest request) {

		OrdersDto createOrder = this.orderService.createOrder(request, userName);

		return new ResponseEntity<OrdersDto>(createOrder, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/")
	public List<OrdersDto> getAll(){
		
		List<OrdersDto> dtos = this.orderService.getAll();
		return dtos;
	}
	
	
	@GetMapping("/{orderId}")
	public OrdersDto getById(@PathVariable int orderId) {
		
		OrdersDto dto = this.orderService.get(orderId);
		return dto;
		
	}
	
	
	@PutMapping("/{orderId}")
	public OrdersDto updateOrder(@PathVariable int orderId, @RequestBody OrdersDto dto) {
		
		OrdersDto updatedDto = this.orderService.updateOrder(dto, orderId);
		return updatedDto;
	}
	
	
	
	@DeleteMapping("/{orderId}")
	public void deleteOrder(@PathVariable int orderId) {
		
		this.orderService.deleteOrder(orderId);
	}
	
	
	
	


}
