package com.hibernate.entitiy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderr")
public class Order {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;
	
	private String orderStatus;
	
	private String paymentStatus;
	
	private Date orderCreated;
	
	private double orderAmount;
	
	private String billingAddress;
	
	private Date orderDelivered;
	
	//Below 2 fields are newly added
	
	private String razorpayOrderId;
	
	private String paymentId;

	@OneToOne
	private User user;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private Set<OrderItem> items = new HashSet<>();
	
	
	
	
	// -----------------------------------------------------------
	

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Order(Integer orderId, String orderStatus, String paymentStatus, Date orderCreated, double orderAmount,
			String billingAddress, Date orderDelivered, String razorpayOrderId, String paymentId, User user,
			Set<OrderItem> items) {
		super();
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.orderCreated = orderCreated;
		this.orderAmount = orderAmount;
		this.billingAddress = billingAddress;
		this.orderDelivered = orderDelivered;
		this.razorpayOrderId = razorpayOrderId;
		this.paymentId = paymentId;
		this.user = user;
		this.items = items;
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Date getOrderDelivered() {
		return orderDelivered;
	}

	public void setOrderDelivered(Date orderDelivered) {
		this.orderDelivered = orderDelivered;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	
	


}
