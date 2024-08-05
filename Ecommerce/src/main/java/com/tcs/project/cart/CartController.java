package com.tcs.project.cart;

import com.tcs.project.product.Product;
import com.tcs.project.product.ProductService;
import com.tcs.project.user.User;
import com.tcs.project.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;
    
//    @PostMapping("/add")
//    public ResponseEntity<String> addToCart(@RequestBody CartRequest cartRequest) {
//        User user = userService.getUserById(cartRequest.getUserId());
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
//        }
//
//        Product product = productService.getProductById(cartRequest.getProductId()).orElse(null);
//        if (product == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
//        }
//
//        Cart cart = new Cart(user, product, cartRequest.getQuantity(), cartRequest.getPrice());
//        cartService.addToCart(cart);
//
//        return ResponseEntity.ok("Product added to cart successfully");
//    }

    @PostMapping("/add/{userId}/{productId}/{quantity}/{price}")
    public ResponseEntity<String> addToCart(@PathVariable Long userId,@PathVariable Long productId,@PathVariable Integer quantity,@PathVariable Double price) {
        User user = userService.getUserById(userId);
        System.out.println(user);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        // Retrieve the product by ID
        Product product = productService.getProductById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        System.out.println(product);
     
        Cart cart = new Cart(user, product, quantity, price);
        cartService.addToCart(cart);

        return ResponseEntity.ok("Product added to cart successfully");
    }
    
//    @DeleteMapping("/remove/{userId}")
//    public ResponseEntity<String> deleteDetailsByUserId(@PathVariable Long userId) {
//        try {
//            cartService.deleteByUserId(userId);
//            return ResponseEntity.ok("Details deleted successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete details");
//        }
//    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<List<Cart>> getCartItems(@PathVariable Long userId) {
        List<Cart> cartItems = cartService.getCartItemsByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<List<Cart>> updateCartItem(
            @PathVariable Long cartItemId, 
            @RequestBody CartUpdateRequest updateRequest) {
        try {
            // Ensure the request contains necessary data
            cartService.updateCartItem(cartItemId, updateRequest.getQuantity(), updateRequest.getProductId());

            // Fetch updated cart items for the user
            List<Cart> cartItems = cartService.getCartItemsByUserId(updateRequest.getUserId());

            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            e.printStackTrace(); // For debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
//    @PutMapping("/update/{cartItemId}")
//    public ResponseEntity<List<Cart>> updateCartItem(@PathVariable Long cartItemId, @RequestBody Cart cart) {
//        cartService.updateCartItem(cartItemId, cart.getQuantity());
//        List<Cart> cartItems = cartService.getCartItemsByUserId(cart.getUser().getId());
//        return ResponseEntity.ok(cartItems);
//    }
    
    @DeleteMapping("/remove/{cartId}/{userId}")
    public void removeCartItem(@PathVariable Long cartId, @PathVariable Long userId) {
    	System.out.println(cartId + userId);
        cartService.removeCartItem(cartId, userId);
    }
}