package com.tcs.project.checkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCheckoutRepository extends JpaRepository<UserCheckout, Long> {
}

