package com.tcs.project.user;

import java.util.HashSet;
import java.util.Set;

import com.tcs.project.product.Product;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    
    @ManyToMany
    @JoinTable(
        name = "wishlist",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> wishlist = new HashSet<>();
    
    public User(Long id) {
		this.id=id;
	}
   
    
    public Set<Product> getWishlist() {
        return wishlist;
    }

    public void setWishlist(Set<Product> wishlist) {
        this.wishlist = wishlist;
    }
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	


    
}
