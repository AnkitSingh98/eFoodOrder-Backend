package com.hibernate.entitiy;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Category {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	public int categoryId;
	
	public String title;
	
	public String description;
	
	public String coverImage;
	
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Product> products = new HashSet<>();
	
	
	

//	public Set<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(Set<Product> products) {
//		this.products = products;
//	}



	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", title=" + title + ", description=" + description
				+ ", coverImage=" + coverImage + ", products=" + products + "]";
	}



	public String getCoverImage() {
		return coverImage;
	}



	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}



	public Category(int categoryId, String title, String description, String coverImage, Set<Product> products) {
	super();
	this.categoryId = categoryId;
	this.title = title;
	this.description = description;
	this.coverImage = coverImage;
	this.products = products;
}



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
	
	

}
