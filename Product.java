package com.EBazaar.Products;


import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String category;
	private String productName;
	private String productDetails;
	private Long price;
	private String reviews;
	
	



	Product(){
		
	}
	@Override
	public String toString() {
		return "Product [category=" + category + ", productName=" + productName + ", productDetails=" + productDetails
				+ ", price=" + price + ", reviews=" + reviews + "]";
	}

	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}


	
	public Product(String category, String productName, String productDetails, Long price, String reviews) {
		super();
		this.category = category;
		this.productName = productName;
		this.productDetails = productDetails;
		this.price = price;
		this.reviews = reviews;
	}
	

}
