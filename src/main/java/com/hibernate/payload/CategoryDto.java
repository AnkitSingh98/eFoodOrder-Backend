package com.hibernate.payload;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CategoryDto {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	public int categoryId;
	
	public String title;
	
	public String description;
	

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CategoryDto [categoryId=" + categoryId + ", title=" + title + ", description=" + description + "]";
	}
	
	

}
