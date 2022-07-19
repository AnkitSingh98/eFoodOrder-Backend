package com.hibernate.controllers;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	
	
	//  Create Product Handler
	@RequestMapping(value="/category/{categoryId}/product",method=RequestMethod.POST)
	public ProductDto createProduct(@RequestBody ProductDto t, @PathVariable int categoryId) {
		
		System.out.println("Inside controller");
		return productService.createProduct(t, categoryId);
	}
	
	
	// Update Product Handler
	@RequestMapping(value="/products/{pid}",method= RequestMethod.PUT)
	public ProductDto updateProduct(@RequestBody ProductDto t,@PathVariable int pid) {
		
		ProductDto updatedProduct = productService.updateProduct(t, pid);
		
		return updatedProduct;
	}
	
	
	
	// Delete by ID Product Handler
	@RequestMapping(value="/products/{pid}",method=RequestMethod.DELETE)
	public void deleteProduct(@PathVariable int pid) {
		
		productService.deleteProduct(pid);
	}
	
	
	// Delete All Product Handler
	@RequestMapping(value="/products",method=RequestMethod.DELETE)
	public void deleteProduct() {
		productService.deleteAll();
	}
	
	
	//Get Single Product Handler
	@RequestMapping(value="/products/{pid}",method=RequestMethod.GET)
	public ProductDto getProduct( @PathVariable int pid) {
		
		ProductDto t = productService.getProduct(pid);
		return t;
	}
	
	
	// Get all Product Handler
	@GetMapping("/products")
	public List<ProductDto> getProduct(){
		
		
		List<ProductDto> allproductDto = productService.getAllProducts();
		
		
		return allproductDto;
		
		
	}
	
	
	
	// Get Products based on category
	@GetMapping("category/{categoryId}/products")
	public List<ProductDto> getProductByCategory(@PathVariable int categoryId){
		
		List<ProductDto> listDto = productService.getProductsByCategory(categoryId);
		
		return listDto;
	}

}
