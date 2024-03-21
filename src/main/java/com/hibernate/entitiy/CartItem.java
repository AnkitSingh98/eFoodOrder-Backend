package com.hibernate.entitiy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartItemId;
	
	@OneToOne
	private Product product;
	
	private int quantity;
	
	private double totalProductPrice;
	
	@JsonIgnore
	@ManyToOne
	private Cart cart;
	
	
	// -------------------------------------------------------

	
	
	
	public int getCartItem() {
		return cartItemId;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	

	public void setCartItem(int cardItemId) {
		this.cartItemId = cartItemId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalProductPrice() {
		return totalProductPrice;
	}

	public void setTotalProductPrice() {
		this.totalProductPrice = this.product.getProductPrice() * this.quantity;
	}
	
	

}
