package com.tcs.project.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tcs.project.user.User;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CartController { 

    @Autowired
    private cartService CartService;

    @PostMapping("/addcart/{id}")
    public Cart addItem(@PathVariable Long id,@RequestBody Cart cart) {
    	 cart.setUser(new User(id));
    	return CartService.addItem( cart);
    }


    @PutMapping("/updated/{cartId}")
    public void updateQuantity(@PathVariable Long cartId, @RequestBody Cart cart) {
        CartService.updateQuantity(cartId, cart);
    }

    @DeleteMapping("/remove")
    public void removeItem(@PathVariable int cartId ) {
    	CartService.removeItem(cartId);
    }

    @GetMapping("/item/{cartId}")
    public List<Cart> getCartItem(@PathVariable Long cartId) {
        return CartService.getCartItem(cartId);
    }
    
    @GetMapping("/itemByUserId/{userId}")
    public List<Cart> getCartItemByUserId(@PathVariable Long userId) {
        return CartService.getCartItemByUserId(userId);
    }

}
