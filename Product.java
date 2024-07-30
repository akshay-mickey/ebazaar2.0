package com.ebazaar.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@NotBlank(message = "Name is mandatory")
	@Size(max = 100, message = "Name can be upto 100 charecters")
	private String productName;

	@NotBlank(message = "Description is mandatory")
	@Size(max = 1000, message = "Description can be upto 1000 charecters")
	private String productDescription;

	@NotNull(message = "Price is mandatory")
	@Positive(message = "price must be positive")
	private Long productPrice;

	@NotNull(message = "Quantity is mandatory")
	@Positive(message = "Quantity must be positive")
	private Long productQuantity;

	@NotBlank(message = "Category is mandatory")
	@Size(max = 100, message = "Category upto 100 charecters only")
	private String category;

	@NotBlank(message = "Brand is mandatory")
	@Size(max = 100, message = "Brand upto 100 charecters only")
	private String productBrand;

	@NotBlank(message = "Image URL is mandatory")
	private String productImage;

	@NotBlank(message = "Specification is mandatory")
	private String productSpecification;

	public Product(Long productId,
			@NotBlank(message = "Name is mandatory") @Size(max = 100, message = "Name can be upto 100 charecters") String productName,
			@NotBlank(message = "Description is mandatory") @Size(max = 1000, message = "Description can be upto 1000 charecters") String productDescription,
			@NotBlank(message = "Price is mandatory") @Positive(message = "price must be positive") Long productPrice,
			@NotBlank(message = "Quantity is mandatory") @Positive(message = "Quantity must be positive") Long productQuantity,
			@NotBlank(message = "Category is mandatory") @Size(max = 100, message = "Category upto 100 charecters only") String category,
			@NotBlank(message = "Brand is mandatory") @Size(max = 100, message = "Brand upto 100 charecters only") String productBrand,
			@NotBlank(message = "Image URL is mandatory") String productImage,
			@NotBlank(message = "Specification is mandatory") String productSpecification) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.category = category;
		this.productBrand = productBrand;
		this.productImage = productImage;
		this.productSpecification = productSpecification;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", productPrice=" + productPrice + ", productQuantity=" + productQuantity
				+ ", category=" + category + ", productBrand=" + productBrand + ", productImage=" + productImage
				+ ", productSpecification=" + productSpecification + "]";
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}

	public Long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Long productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductSpecification() {
		return productSpecification;
	}

	public void setProductSpecification(String productSpecification) {
		this.productSpecification = productSpecification;
	}

	public Product() {

	}

}
