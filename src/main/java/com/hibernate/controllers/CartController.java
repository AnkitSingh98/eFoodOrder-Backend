package com.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.payload.CartDto;
import com.hibernate.payload.ItemRequest;
import com.hibernate.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	String userName = "ank99@gmail.com";
	
	@PostMapping("/")
	public CartDto addItemToCart(@RequestBody ItemRequest item) {
		
		System.out.println("Inside controller");
		
		
		
		CartDto t = this.cartService.addItem(item, userName);
		return t;
		
	}
	
	
	
	@GetMapping("/")
	public CartDto getByUsername()  {  
		
		System.out.println("Inside controller");
		
		
		
		CartDto t = this.cartService.get(userName);
		
		System.out.println("CartId= "+t.getCartId()+" \n User= "+ t.getUser() +" \nItems=" + t.getItems());
		
		return t;
		
	}
	
	
	
	@PutMapping("/{productId}")
	public CartDto removeProductFromcart(@PathVariable int productId) {
		CartDto cartDto = this.cartService.removeItem(userName, productId);
		return cartDto;
	}

}
