package com.AmazonClone.service;

import com.AmazonClone.payload.CartDto;
import com.AmazonClone.payload.CartItemDto;

import java.util.List;

public interface CartService {
    CartDto getCartByUserId(int userId);

    CartDto createCart(int userId);

    CartDto addToCart(int cartId, CartItemDto cartItemDto);

    CartDto updateCartItemQuantity(int cartId, int cartItemId, int quantity);

    void removeCartItem(int cartId, int cartItemId);

    void clearCart(int cartId);
}

