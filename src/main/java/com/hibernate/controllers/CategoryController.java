package com.hibernate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.payload.CategoryDto;
import com.hibernate.services.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	public CategoryService categoryService;
	
	
	//Create Category
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/category")
	public ResponseEntity<CategoryDto> createCategory( @RequestBody CategoryDto t) {
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println("Inside Category controller ");
		 CategoryDto createdCatDto = this.categoryService.createCategory(t);
		 return new ResponseEntity<CategoryDto>(createdCatDto,HttpStatus.CREATED);
		
	}
	
	
	//UpdateCategory
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/category/{cid}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto t, @PathVariable int cid) {
		
		CategoryDto updatedCatDto = this.categoryService.updateCategory(t, cid);
		return new ResponseEntity<CategoryDto>(updatedCatDto,HttpStatus.OK);
	}
	
	
	//Delete Category by Id
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/category/{cid}")
	public ResponseEntity<String> deleteCategory(@PathVariable int cid) {
		
		this.categoryService.deleteCategory(cid);
		return new ResponseEntity<String>("Category is deleted successfully !!", HttpStatus.OK);
	}
	
	
	//Get category by Id
	@GetMapping("/category/{cid}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int cid) {
		
		CategoryDto dto = this.categoryService.getCategory(cid);
		return new ResponseEntity<CategoryDto>(dto, HttpStatus.OK);
		
	}
	
	
	
	//Get all Category
	@GetMapping("/category")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		
		List<CategoryDto> list = this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(list,HttpStatus.OK);
		
	}

}
