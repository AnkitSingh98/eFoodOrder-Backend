package com.hibernate.services.Impl;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.entitiy.Cart;
import com.hibernate.entitiy.CartItem;
import com.hibernate.entitiy.Product;
import com.hibernate.entitiy.User;
import com.hibernate.payload.CartDto;
import com.hibernate.payload.ItemRequest;
import com.hibernate.repository.CartRepository;
import com.hibernate.repository.ProductRepository;
import com.hibernate.repository.UserRepository;
import com.hibernate.services.CartService;
import com.hibernate.exception.ResourceNotFoundException;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	// add item to cart
	// we will check the availability of cart if cart is available then we will add item to cart
	// otherwise we will create a new cart and add the item to it
	@Override
	public CartDto addItem(ItemRequest item, String username) {
	
		
		
		int productId = item.getProductId();
		int quantity = item.getQuantity();
		
		if (quantity <= 0) {
			throw new ResourceNotFoundException("Invalid Quantity !!");			
		}
		
		// Get User from username
		User u = this.userRepository.findByEmail(username).orElseThrow( ()-> new ResourceNotFoundException());
		
		System.out.println("Inside Service: User = " + u.getName());
		
		//get the product from db
		Product p = this.productRepo.findById(productId).orElseThrow( ()-> new ResourceNotFoundException());
		System.out.println("Inside Service: Product= " + p);
		
		if(!p.isStock()) {
			throw new ResourceNotFoundException("Product is out of stock");
		}
		
		
		// Create cartItem: As we have to add cartItem in Cart and not Product
		CartItem cartItem = new CartItem();
		System.out.println("CartItem: "+cartItem);
		cartItem.setProduct(p);
		cartItem.setQuantity(quantity);
		cartItem.setTotalProductPrice();
		
		System.out.println("CartItem: "+cartItem.getCartItem()+" --> "+cartItem.getCartItemId()+ " ---> " + cartItem.getProduct().getProductName());
		
		// Get cart of user
		Cart cart = u.getCart();
		
		
		if(cart == null) {
			cart = new Cart();
			
			//ye step bhool gye sir !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			cart.setUser(u);
		}
		
		System.out.println("Cart of User = : "+cart.getCartId() + " ---> " + cart.getUser() + " ----> " + cart.getItems().size());
		
		Set<CartItem> items = cart.getItems();
		
//		 Till now what is happening is if we add same product more than one time, it creates new cart item and adds it there
//		 So, to avoid this and add it in the same cart item BELOW CODE
//		 If cart item already present for that productId then just update the quantity
//		else create new cart item
//				
		AtomicReference<Boolean> flag = new AtomicReference<>(false);

		Set<CartItem> newItems = items.stream().map((i) -> {
			// changes
			if (i.getProduct().getProductId() == p.getProductId()) {

				i.setQuantity(quantity);
				i.setTotalProductPrice();
				flag.set(true);
			}
			return i;
		}).collect(Collectors.toSet());

		// check
		if (flag.get()) {
			
			// newItems ko items ki jagah set karenge
			items.clear();
			items.addAll(newItems);
			
			// No need to save CartItem because we are using Cascade.All
			
		} else {
			
			// add cart in cartItem only if it is a newly created cart
			cartItem.setCart(cart);
			
			items.add(cartItem);
			// No need to save CartItem because we are using Cascade.All
			
		}		
				
		
		
//		//niche wala step we can skip also as we are using orphan = true in cart.java !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//		//cart.setItems(items);
		
	
		Cart updatedCart = this.cartRepository.save(cart);
		return this.mapper.map(updatedCart, CartDto.class);
		
		
		
	}
	
	
	
	

	@Override
	public CartDto get(String userName) {
		
		User u = this.userRepository.findByEmail(userName).orElseThrow( ()-> new ResourceNotFoundException());
		
		Cart cart = this.cartRepository.findByUser(u).orElseThrow( ()-> new ResourceNotFoundException());
		
		return this.mapper.map(cart, CartDto.class);
		
	}

	
	
	
	@Override
	public CartDto removeItem(String username, int productId) {
		
		User user = this.userRepository.findByEmail(username).orElseThrow( ()-> new ResourceNotFoundException());

		Cart cart = user.getCart();

		Set<CartItem> items = cart.getItems();

		boolean result = items.removeIf((item) -> item.getProduct().getProductId() == productId);
		

		Cart updatedCart = cartRepository.save(cart);
		// No need to save items because we are using Cascade.ALL

		return this.mapper.map(updatedCart, CartDto.class);
	}
	
	
	
	@Override
	public CartDto clearCart(String username) {
		
		User user = this.userRepository.findByEmail(username).orElseThrow( ()-> new ResourceNotFoundException());

		Cart cart = user.getCart();

		Set<CartItem> items = cart.getItems();

		boolean result = items.removeAll(items);
		
		
		Cart updatedCart = cartRepository.save(cart);
		// No need to save items because we are using Cascade.ALL

		return this.mapper.map(updatedCart, CartDto.class);
	}
	
	
	
	
	

}
