package com.hibernate.payload;

public class OrderRequest {
	
	
	private int cartId;
	private String address;
	private String razorpayOrderId;
	
	
	
	@Override
	public String toString() {
		return "OrderRequest [cartId=" + cartId + ", address=" + address + ", razorpayOrderId=" + razorpayOrderId + "]";
	}
	
	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}
	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}
	public OrderRequest(int cartId, String address, String razorpayOrderId) {
		super();
		this.cartId = cartId;
		this.address = address;
		this.razorpayOrderId = razorpayOrderId;
	}
	
	
	
	

}
