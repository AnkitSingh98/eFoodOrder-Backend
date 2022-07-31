package com.hibernate.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.entitiy.Cart;
import com.hibernate.entitiy.CartItem;
import com.hibernate.entitiy.Order;
import com.hibernate.entitiy.OrderItem;
import com.hibernate.entitiy.Product;
import com.hibernate.entitiy.User;
import com.hibernate.exception.ResourceNotFoundException;
import com.hibernate.payload.OrderRequest;
import com.hibernate.payload.OrdersDto;
import com.hibernate.repository.CartRepository;
import com.hibernate.repository.OrdersRepository;
import com.hibernate.repository.ProductRepository;
import com.hibernate.repository.UserRepository;
import com.hibernate.services.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	
	@Autowired
	private OrdersRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;

	
	
	@Override
	public OrdersDto createOrder(OrderRequest request, String username) {
		
		User user = this.userRepository.findByEmail(username).orElseThrow( ()-> new ResourceNotFoundException());
		int cartId = request.getCartId();
		String address = request.getAddress();

		Cart cart = this.cartRepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException());

		Set<CartItem> items = cart.getItems();

		Order order = new Order();
		AtomicReference<Double> totalOrderPrice = new AtomicReference<>(0.0);
		
		//loop through all cart items and set in order items
		Set<OrderItem> orderItems = items.stream().map((cartItem) -> {

			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalProductPrice(cartItem.getTotalProductPrice());
			orderItem.setOrder(order);
			totalOrderPrice.set(totalOrderPrice.get() + orderItem.getTotalProductPrice());

			
			// Reduce this much quantity from product table also as order has been placed ( fetch - update - save )
			
			int productId = orderItem.getProduct().getProductId();
			// product:- fetch
			Product product = this.productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException());
	    	//update the product quantity
	    	int q=cartItem.getQuantity();
	    	product.setProductQuantity(product.getProductQuantity()-q);
	    	//save the product
	    	this.productRepository.save(product);

	    	
	    	
			return orderItem;

		}).collect(Collectors.toSet());

		order.setItems(orderItems);
		order.setBillingAddress(address);
		order.setPaymentStatus("NOT PAID");
		order.setOrderAmount(totalOrderPrice.get());
		order.setOrderCreated(new Date());
		order.setOrderDelivered(null);
		order.setOrderStatus("CREATED");
		order.setUser(user);

		Order savedOrder = this.orderRepository.save(order);

		
		// Clear cart items after order is placed
		cart.getItems().clear();

		// this is not needed i think check it once
		this.cartRepository.save(cart);

		return this.mapper.map(savedOrder, OrdersDto.class);
		
		
	}

	@Override
	public OrdersDto updateOrder(OrdersDto orderDto, int orderId) {
		
		Order myOrder = this.orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException());
		
		String orderStatus = orderDto.getOrderStatus();
		String paymentStatus = orderDto.getPaymentStatus();
		
		myOrder.setOrderStatus(orderStatus);
		myOrder.setPaymentStatus(paymentStatus);
		
		Order updatedOrder = this.orderRepository.save(myOrder);
		
		return this.mapper.map(updatedOrder, OrdersDto.class);
	}

	@Override
	public void deleteOrder(int orderId) {
		
		Order order = this.orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException());
		this.orderRepository.delete(order);

	}

	@Override
	public List<OrdersDto> getAll() {
		
		List<Order> orders = this.orderRepository.findAll();
		List<OrdersDto> dtos = orders.stream().map( (o) -> this.mapper.map(o, OrdersDto.class)).collect(Collectors.toList());
		
		return dtos;
		
	}

	@Override
	public OrdersDto get(int orderId) {
		
		Order order = this.orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException());
		OrdersDto dto = this.mapper.map(order, OrdersDto.class);
		
		return dto;
	}

}
