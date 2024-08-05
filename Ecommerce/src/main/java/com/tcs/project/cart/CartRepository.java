package com.tcs.project.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    
    List<Cart> findByUserId(Long userId);
    Cart findByProductIdAndUserId(Long productId, Long userId);
//    void deleteByProductIdAndUserId(Long productId, Long userId);
//    void deleteByIdAndUserId(Long cartId, Long userId);

}
