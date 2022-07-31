package com.hibernate.payload;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.hibernate.entitiy.CartItem;
import com.hibernate.entitiy.User;

public class CartDto {

	
	private int cartId;
	
	private User user;
	
	private Set<CartItemDto> items = new HashSet<>();

	
	
	
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<CartItemDto> getItems() {
		return items;
	}

	public void setItems(Set<CartItemDto> items) {
		this.items = items;
	}
	
	
	
	
	
	
	
	
}
