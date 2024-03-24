package com.hibernate.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.entitiy.Category;
import com.hibernate.entitiy.Product;
import com.hibernate.exception.ResourceNotFoundException;
import com.hibernate.payload.CategoryDto;
import com.hibernate.repository.CategoryRepository;
import com.hibernate.repository.ProductRepository;
import com.hibernate.services.CategoryService;

@Service
public class CategoryServicecImpl implements CategoryService {
	
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto t) {
		Category c = this.toEntity(t);
		
		Category createdCategory =  this.categoryRepository.save(c);
		return this.toDto(createdCategory);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto t, int cid) {
		
		Category c = this.categoryRepository.findById(cid).orElseThrow( ()-> new ResourceNotFoundException());
		c.setTitle(t.getTitle());
		c.setDescription(t.getDescription());
		c.setCoverImage(t.getCoverImage());
		
		Category updatedCategory = this.categoryRepository.save(c);
		return this.toDto(updatedCategory);
	}

	@Override
	public void deleteCategory(int cid) {
		
		Category c = this.categoryRepository.findById(cid).orElseThrow( ()-> new ResourceNotFoundException());
		this.categoryRepository.delete(c);

	}

	@Override
	public CategoryDto getCategory(int cid) {
		
		Category c = this.categoryRepository.findById(cid).orElseThrow( ()-> new ResourceNotFoundException());
		
		return this.toDto(c);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		
		List<Category> list = this.categoryRepository.findAll();
		List<CategoryDto> listDto = list.stream().map(c -> this.toDto(c)).collect(Collectors.toList());
		return listDto;
	}
	
	
	public CategoryDto toDto(Category c) {
		
		CategoryDto t = new CategoryDto();
		t.setCategoryId(c.getCategoryId());
		t.setTitle(c.getTitle());
		t.setDescription(c.getDescription());
		t.setCoverImage(c.getCoverImage());
		return t;
	}
	
	public Category toEntity(CategoryDto t) {
		Category c = new Category();
		c.setCategoryId(t.getCategoryId());
		c.setTitle(t.getTitle());
		c.setDescription(t.getDescription());
		c.setCoverImage(t.getCoverImage());
		return c;
	}

}
