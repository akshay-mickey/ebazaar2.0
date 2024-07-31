package com.tcs.project.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/checkouts")
public class UserCheckoutController {

    @Autowired
    private UserCheckoutService checkoutService;

    @GetMapping
    public List<UserCheckout> getAllCheckouts() {
        return checkoutService.getAllCheckouts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserCheckout> getCheckoutById(@PathVariable Long id) {
        Optional<UserCheckout> checkout = checkoutService.getCheckoutById(id);
        return checkout.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<UserCheckout> createCheckout(@RequestBody UserCheckout checkout) {
        UserCheckout createdCheckout = checkoutService.saveCheckout(checkout);
        return ResponseEntity.ok(createdCheckout);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCheckout> updateCheckout(@PathVariable Long id, @RequestBody UserCheckout checkout) {
        if (!checkoutService.getCheckoutById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        checkout.setId(id);
        UserCheckout updatedCheckout = checkoutService.saveCheckout(checkout);
        return ResponseEntity.ok(updatedCheckout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckout(@PathVariable Long id) {
        if (!checkoutService.getCheckoutById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        checkoutService.deleteCheckout(id);
        return ResponseEntity.noContent().build();
    }
}
