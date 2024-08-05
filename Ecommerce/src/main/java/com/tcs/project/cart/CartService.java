package com.tcs.project.cart;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    
    public List<Cart> getCartItemsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }
    
    public void deleteByUserId(Long userId)
    {
    	cartRepository.deleteById(userId);
    }
    
    public void removeCartItem(Long cartId, Long userId) {
    	Cart cart = cartRepository.findById(cartId).get();
    	cartRepository.delete(cart);
    }
    public void updateCartItem(Long cartItemId, Integer newQuantity, Long productId) {
        Cart cart = cartRepository.findById(cartItemId)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Optionally, you can check if the productId matches
        if (!cart.getProduct().getId().equals(productId)) {
            throw new RuntimeException("Product ID does not match.");
        }

        // Update the quantity
        cart.setQuantity(newQuantity);
        cartRepository.save(cart);
    }


    
//    public void updateCartItem(Long cartItemId, Integer quantity) {
//    	Cart cart = cartRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Cart item not found"));
//        cart.setQuantity(quantity);
//
//        // Recalculate price
//        double productPrice = cart.getProduct().getPrice();
//        double newPrice = productPrice * quantity;  // Direct multiplication
//
//        cart.setPrice(newPrice);
//        cartRepository.save(cart);
//    }

}