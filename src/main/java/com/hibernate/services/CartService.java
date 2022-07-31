package com.hibernate.services;



import com.hibernate.payload.CartDto;
import com.hibernate.payload.ItemRequest;



public interface CartService {
	
	// add item to cart
	// we will check the availability of cart if cart is available then we will add item to cart
	// otherwise we will create a new cart and add the item to it
	
	CartDto addItem(ItemRequest item, String username);
	
	
	// get Cart Of User
	
	CartDto get(String userName);
	
	// remove item from cart
	
	CartDto removeItem(String username, int itemId);
		
	

}
