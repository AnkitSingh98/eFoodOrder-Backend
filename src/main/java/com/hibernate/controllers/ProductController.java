package com.hibernate.controllers;

import java.io.*;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import com.hibernate.entitiy.Product;
import com.hibernate.payload.ProductDto;
import com.hibernate.services.FileUploadService;
import com.hibernate.services.ProductService;
import com.hibernate.utility.ImageResponse;


@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	
	@Value("${product.images.path}")
	 private String imagePath;


	    //upload the file for product image

	    @PostMapping("/products/images/{productId}")
	    public ResponseEntity<?> uploadImageOfProduct(
	            @PathVariable int productId,
	            @RequestParam("productImage") MultipartFile file
	    ) {

	        ProductDto product = this.productService.getProduct(productId);
	        String imageName = null;
	        try {
	            imageName = this.fileUploadService.uploadFile( file, imagePath);
	            product.setImageName(imageName);
	            
	            productService.updateProduct(product, productId);
	            
	            ImageResponse imageResponse = new ImageResponse(imageName, "Image created successfully", true, HttpStatus.CREATED);
	    		return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

	        } catch (IOException e) {
	            e.printStackTrace(); 
	           // return new ResponseEntity<>(Map.ofEntries("message", "File not uploaded on server !!"), HttpStatus.INTERNAL_SERVER_ERROR);
	            return new ResponseEntity<>("File not uploaded on server !!", HttpStatus.INTERNAL_SERVER_ERROR);
	        }


	    }

	    //get the image of given product
	    @GetMapping("/products/images/{productId}")
	    public void downloadImage(@PathVariable int productId, HttpServletResponse response) throws IOException {
	        ProductDto product = this.productService.getProduct(productId);
	        String imageName = product.getImageName();
	        
	        InputStream resource = this.fileUploadService.getResource(imagePath, imageName);
	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        OutputStream outputStream = response.getOutputStream();
	        StreamUtils.copy(resource, outputStream);

	    }

	
	

	 //create
	 @PreAuthorize("hasRole('ADMIN')")
	 @PostMapping("/product")
	 public ResponseEntity<ProductDto> createProductWithoutCategory(@RequestBody ProductDto productDto) {
	      ProductDto createdProduct = productService.createProductWithoutCategory(productDto);
	      return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	  }
	
	
	
	//  Create Product Handler
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/category/{categoryId}/product",method=RequestMethod.POST)
	public ProductDto createProductWithCategory(@RequestBody ProductDto t, @PathVariable int categoryId) {
		
		System.out.println("Inside controller");
		return productService.createProductWithCategory(t, categoryId);
	}
	
	
	// Update Product Handler
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/products/{pid}",method= RequestMethod.PUT)
	public ProductDto updateProduct(@RequestBody ProductDto t,@PathVariable int pid) {
		
		ProductDto updatedProduct = productService.updateProduct(t, pid);
		
		return updatedProduct;
	}
	
	
	// Update Product Category
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/product/{pid}/category/{cid}",method= RequestMethod.PUT)
	public ProductDto updateProductCategory( @PathVariable int pid, @PathVariable int cid) {
		
		ProductDto updatedProduct = productService.updateProductCategory(pid, cid);
		return updatedProduct;
	}
	
	
	
	// Delete by ID Product Handler
	@PreAuthorize("hasRole('ADMIN')")
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
