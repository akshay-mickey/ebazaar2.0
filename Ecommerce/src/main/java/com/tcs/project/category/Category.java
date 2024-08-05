package com.tcs.project.category;

import java.util.Set;

import com.tcs.project.product.Product;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageUrl;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Category(Long id, String name, String imageUrl, Set<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.products = products;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
