package com.hibernate.payload;

public class CartItemDto {
	

	private int cartItemId;
	
	private ProductDto product;
	
	private int quantity;
	
	private double totalProductPrice;
	
	
	
	
	

	public void setTotalProductPrice(double totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
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
