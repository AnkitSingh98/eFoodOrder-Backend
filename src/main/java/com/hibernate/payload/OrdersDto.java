package com.hibernate.payload;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class OrdersDto {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer orderId;
	
	private String orderStatus;
	
	private String paymentStatus;
	
	private Date orderCreated;
	
	private double orderAmount;
	
	private String billingAddress;
	
	private Date orderDelivered;
	
	

}
