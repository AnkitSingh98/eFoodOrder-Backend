package com.hibernate.controllers;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.razorpay.*;


@RestController
public class PaymentController {
	
	// create order for payment
	
	@PostMapping("/payment/create_order/{amountToPay}")
	public String createOrder( @PathVariable int amountToPay) throws RazorpayException {
		
		System.out.println("Payment order created!!");
		
		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_itMZbddnuzzYl5", "YEXWZX35WlEFO9mzdStqi3iZ");
		
		JSONObject ob = new JSONObject();
		ob.put("amount", amountToPay*100); 
		ob.put("currency", "INR");
		ob.put("receipt", "txn_235425");
		
		// creating new order using RazorpayClient
		
		Order order = razorpayClient.orders.create(ob);
		System.out.println(order);
		
		// save the order in database
		
		
		
		
		
		return order.toString();
	}

}
