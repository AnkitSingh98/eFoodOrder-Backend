package com.hibernate.services;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hibernate.entitiy.Product;
import com.hibernate.payload.ProductDto;
import com.hibernate.repository.ProductRepository;


public interface ProductService {
	
	public ProductDto createProductWithCategory(ProductDto t, int categoryId);
	
	public ProductDto createProductWithoutCategory(ProductDto t);
	
	public ProductDto updateProduct(ProductDto t, int pid);  
	
	public ProductDto updateProductCategory(int pid, int cid); 
	
	public void deleteProduct(int pid);
	
	public void deleteAll();
	
	public ProductDto getProduct(int pid);
	
	public List<ProductDto> getAllProducts();
	
	public List<ProductDto> getProductsByCategory(int categoryId);
	
	
	
}
