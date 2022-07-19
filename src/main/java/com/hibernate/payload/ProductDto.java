package com.hibernate.payload;

import javax.persistence.Id;

import com.hibernate.entitiy.Category;

public class ProductDto {
	
	@Id
	private int productId;
	private String productName;
	private String productDesc;
	private double productPrice;
	
	private int productQuantity;
	
	private boolean live;
	
	private boolean stock;
	
	private String imageName;
	
	private Category category;
	
	
	
	
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public boolean isStock() {
		return stock;
	}
	public void setStock(boolean stock) {
		this.stock = stock;
	}
	
	

}
