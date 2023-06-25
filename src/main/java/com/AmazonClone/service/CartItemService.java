package com.AmazonClone.service;

import com.AmazonClone.payload.CartItemDto;

import java.util.List;

public interface CartItemService {

    CartItemDto createCartItem(CartItemDto cartItemDto);
    CartItemDto updateCartItem(int cartItemId, CartItemDto cartItemDto);
    void deleteCartItem(int cartItemId);
    List<CartItemDto> getAllCartItems();
    CartItemDto getCartItemById(int cartItemId);
}
