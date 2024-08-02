package com.tcs.project.wishlist;

import java.time.LocalDateTime;

import com.tcs.project.product.Product;
import com.tcs.project.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
	@Table(name = "wishlist")
	public class Wishlist {

	    @Id
	    @GeneratedValue
	    (strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;
	    
	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;
	    
	    
	    private LocalDateTime addedAt;

	    public Wishlist() {
	        this.addedAt = LocalDateTime.now(); // Initialize addedAt with the current timestamp
	    }
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

//		public LocalDateTime getAddedAt() {
//			return addedAt;
//		}

//		public void setAddedAt(LocalDateTime addedAt) {
//			this.addedAt = addedAt;
//		}

		public Wishlist(Long id, User user, Product product, LocalDateTime addedAt) {
			super();
			this.id = id;
			this.user = user;
			this.product = product;
			this.addedAt = addedAt;
		}

		@Override
		public String toString() {
			return "Wishlist [id=" + id + ", user=" + user + ", product=" + product + ", addedAt=" + addedAt + "]";
		}

		
	    
	    // Getters and Setters
	    
	    
	    
	}

	

