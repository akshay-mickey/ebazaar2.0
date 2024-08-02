package com.tcs.project.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCheckoutService {

    @Autowired
    private UserCheckoutRepository checkoutRepository;

    public List<UserCheckout> getAllCheckouts() {
        return checkoutRepository.findAll();
    }

    public Optional<UserCheckout> getCheckoutById(Long id) {
        return checkoutRepository.findById(id);
    }

    public UserCheckout saveCheckout(UserCheckout checkout) {
        return checkoutRepository.save(checkout);
    }

    public void deleteCheckout(Long id) {
        checkoutRepository.deleteById(id);
    }
}

