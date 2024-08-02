package com.tcs.project.wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tcs.project.product.Product;

import java.util.Set;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Add product to user's wishlist
    @PostMapping("/add")
    public ResponseEntity<String> addProductToWishlist(
            @RequestParam Long userId,
            @RequestParam Long productId) {
        try {
            wishlistService.addProductToWishlist(userId, productId);
            return ResponseEntity.ok("Product added to wishlist successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Remove product from user's wishlist
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeProductFromWishlist(
            @RequestParam Long userId,
            @RequestParam Long productId) {
        try {
            wishlistService.removeProductFromWishlist(userId, productId);
            return ResponseEntity.ok("Product removed from wishlist successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get all products in a user's wishlist
    @GetMapping("/user/{userId}")
    public ResponseEntity<Set<Product>> getUserWishlist(@PathVariable Long userId) {
        try {
            Set<Product> wishlist = wishlistService.getUserWishlist(userId);
            return ResponseEntity.ok(wishlist);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
