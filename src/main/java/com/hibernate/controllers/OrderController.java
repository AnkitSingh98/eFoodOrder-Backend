package com.hibernate.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	// String userName = "ank99@gmail.com";

	@Autowired
	private OrdersService orderService;

	@PostMapping("/create-order")
	public ResponseEntity<OrdersDto> createOrder(@RequestBody OrderRequest request, Principal principal) {
		
		OrdersDto createOrder = this.orderService.createOrder(request, principal.getName());

		return new ResponseEntity<OrdersDto>(createOrder, HttpStatus.CREATED);
	}
	
	
	@GetMapping("")
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
	
	
	
	// update order payment status and payment id
	
	@PutMapping("/payment/{orderId}/{paymentId}")
	public OrdersDto updateOrderPayment(@PathVariable int orderId, @PathVariable String paymentId) {
		
		OrdersDto updatedDto = this.orderService.updateOrderPayment(orderId, paymentId);
		return updatedDto;
		
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{orderId}")
	public void deleteOrder(@PathVariable int orderId) {
		
		this.orderService.deleteOrder(orderId);
	}
	
	
	
	
	  @GetMapping("/getOrder")
	    public ResponseEntity<List<OrdersDto>> getOrders(Principal principal) {
	        return new ResponseEntity<>(this.orderService.getOrderOfUser(principal.getName()), HttpStatus.OK);
	    }
	
	
	
	


}
