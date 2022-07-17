package com.hibernate.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.entitiy.Product;
import com.hibernate.exception.ResourceNotFoundException;
import com.hibernate.payload.ProductDto;
import com.hibernate.repository.ProductRepository;
import com.hibernate.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository  productRepository;
	 
	 
	
	// Create Product ********
	@Override
	public ProductDto createProduct(ProductDto t) {
		
		 Product p = this.toEntity(t);
		 Product createdProduct = this.productRepository.save(p);
		 
		 return this.toDto(createdProduct);
		 
	}
	
	
	
	// Update  **********
	@Override
	public ProductDto updateProduct(ProductDto t, int pid) {
		
		Product p = this.productRepository.findById(pid).orElseThrow( ()-> new ResourceNotFoundException("User not found "+pid) );
		p.setProductName(t.getProductName());
		p.setProductDesc(t.getProductDesc());
		p.setProductPrice(t.getProductPrice());
		p.setStock(t.isStock());
		
		Product updatedProduct = this.productRepository.save(p);
		return this.toDto(updatedProduct);
		
	}
	
	
	
	
	// Delete by id ***********
	@Override
	public void deleteProduct(int pid) {
		
		Product p = productRepository.findById(pid).orElseThrow( ()-> new ResourceNotFoundException());
		productRepository.delete(p);
	}
	
	
	// Delete All ***********
	@Override
	public void deleteAll() {
		
	 productRepository.deleteAll();
	}
	
	
	
	// Get single Product  **********
	@Override
	public ProductDto getProduct(int pid) {
		
		Product p =  productRepository.findById(pid).orElseThrow( ()-> new ResourceNotFoundException());
		return this.toDto(p);
	}
	
	
	
	// Get all Product  **********
	@Override
	public List<ProductDto> getAllProducts(){
		
		
		List<Product> list = productRepository.findAll();
		List<ProductDto> listDto = list.stream().map(p->this.toDto(p)).collect(Collectors.toList());
		
		return listDto;
		
	}
	
	
	
	public ProductDto toDto(Product p) {
		
		ProductDto t = new ProductDto();
		t.setProductId(p.getProductId());
		t.setProductName(p.getProductName());
		t.setProductDesc(p.getProductDesc());
		t.setProductPrice(p.getProductPrice());
		t.setStock(p.isStock());
		
		return t;
	}
	
	public Product toEntity(ProductDto t) {
		
		Product p = new Product();
		p.setProductId(t.getProductId());
		p.setProductName(t.getProductName());
		p.setProductDesc(t.getProductDesc());
		p.setProductPrice(t.getProductPrice());
		p.setStock(t.isStock());
		
		return p;
	}

}
