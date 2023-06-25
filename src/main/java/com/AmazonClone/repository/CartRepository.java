package com.AmazonClone.repository;

import com.AmazonClone.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    // Add custom method declarations for cart-related operations

    // Get cart by user ID
    Cart findByUserId(int userId);
}

