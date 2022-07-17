package com.hibernate.entitiy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

	@OneToOne
	private User user;
	
	@OneToMany(mappedBy = "order")
	private Set<OrderItem> items = new HashSet<>();
	
	
	
	
	
	
	
	
	// -----------------------------------------------------------
	
	
	
	
	

	public Order(Integer orderId, String orderStatus, String paymentStatus, Date orderCreated, double orderAmount,
			String billingAddress, Date orderDelivered, User user, Set<OrderItem> items) {
		super();
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.orderCreated = orderCreated;
		this.orderAmount = orderAmount;
		this.billingAddress = billingAddress;
		this.orderDelivered = orderDelivered;
		this.user = user;
		this.items = items;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
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
