package com.tcs.project.wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.project.product.Product;
import com.tcs.project.product.ProductRepository;
import com.tcs.project.user.User;
import com.tcs.project.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class WishlistService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;
   
    @Autowired
    WishlistRepository wishlistRepository;
    
    // Add product to user's wishlist
    public void addProductToWishlist(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        user.getWishlist().add(product);
        LocalDateTime localDateTime = LocalDateTime.now();
		//LocalDate localDate = localDateTime.toLocalDate();
      
        
      //  userRepository.save(user);
        
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
       // wishlist.setAddedAt(localDateTime );
        // No need to explicitly set addedAt; it will be set to current timestamp by default
      
        wishlistRepository.save(wishlist);
    }

    // Remove product from user's wishlist
    public void removeProductFromWishlist(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        user.getWishlist().remove(product);
        userRepository.save(user);
    }

    // Get all products in a user's wishlist
    public Set<Product> getUserWishlist(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getWishlist();
    }
}
