package com.ebazaar.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebazaar.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class cartService {

    @Autowired
    private CartRepository CartRepository;

    public Cart addItem( Cart cart) {
        
    	LocalDateTime localDateTime = LocalDateTime.now();
		//LocalDate localDate = localDateTime.toLocalDate();
       cart.setAddedAt(localDateTime );
        
        return CartRepository.save(cart);
    }

    public void updateQuantity(Long cartID,Cart cart) {
         cart = (Cart) CartRepository.findByCartId(cartID);
               // .orElseThrow(() -> new RuntimeException("Item not found in cart"));
        LocalDateTime localDateTime = LocalDateTime.now();
        cart.setAddedAt(localDateTime );
     
        CartRepository.save(cart);
    }

    public void removeItem(int id) {
        CartRepository.deleteById(id);
    }

    public List<Cart> getCartItem(Long cartId) {
        return CartRepository.findByCartId(cartId);
            //    .orElseThrow(() -> new RuntimeException("Item not found in cart"));
    }

	public List<Cart> getCartItemByUserId(Long  userId) {
		return CartRepository.findByUserId(userId);
	}

	
	
}
