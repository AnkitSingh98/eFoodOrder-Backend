package com.hibernate.controllers;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.entitiy.Product;
import com.hibernate.payload.ProductDto;
import com.hibernate.services.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	
	
	//  Create Product Handler
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ProductDto createProduct(@RequestBody ProductDto t) {
		
		return productService.createProduct(t);
	}
	
	
	// Update Product Handler
	@RequestMapping(value="/{pid}",method= RequestMethod.PUT)
	public ProductDto updateProduct(@RequestBody ProductDto t,@PathVariable int pid) {
		
		ProductDto updatedProduct = productService.updateProduct(t, pid);
		
		return updatedProduct;
	}
	
	
	
	// Delete by ID Product Handler
	@RequestMapping(value="/{pid}",method=RequestMethod.DELETE)
	public void deleteProduct(@PathVariable int pid) {
		
		productService.deleteProduct(pid);
	}
	
	
	// Delete All Product Handler
	@RequestMapping(value="/",method=RequestMethod.DELETE)
	public void deleteProduct() {
		productService.deleteAll();
	}
	
	
	//Get Single Product Handler
	@RequestMapping(value="/{pid}",method=RequestMethod.GET)
	public ProductDto getProduct( @PathVariable int pid) {
		
		ProductDto t = productService.getProduct(pid);
		return t;
	}
	
	
	// Get all Product Handler
	@RequestMapping(value="/",method=RequestMethod.GET)
	public List<ProductDto> getProduct(){
		
		
		List<ProductDto> allproductDto = productService.getAllProducts();
		return allproductDto;
		
		
	}

}
