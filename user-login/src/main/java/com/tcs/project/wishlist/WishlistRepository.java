package com.tcs.project.wishlist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.project.product.Product;
import com.tcs.project.user.User;


	
	public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
	    
	    List<Wishlist> findByUser(User user);
	    
	    List<Wishlist> findByProduct(Product product);
	}



