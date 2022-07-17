package com.hibernate.services;

import java.util.List;

import com.hibernate.payload.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto t);
	
	public CategoryDto updateCategory(CategoryDto t, int cid);
	
	public void deleteCategory(int cid);
	
	public CategoryDto getCategory(int cid);
	
	public List<CategoryDto> getAllCategory();

}
