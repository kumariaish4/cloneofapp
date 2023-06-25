package com.AmazonClone.controller;

import com.AmazonClone.payload.CartItemDto;
import com.AmazonClone.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public ResponseEntity<CartItemDto> createCartItem(@RequestBody CartItemDto cartItemDto) {
        CartItemDto createdCartItem = cartItemService.createCartItem(cartItemDto);
        return new ResponseEntity<>(createdCartItem,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable("id") int cartItemId, @RequestBody CartItemDto cartItemDto) {
        CartItemDto updatedCartItem = cartItemService.updateCartItem(cartItemId, cartItemDto);
        return new ResponseEntity<>(updatedCartItem,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable("id") int cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
    }

    @GetMapping
    public List<CartItemDto> getAllCartItems() {
        List<CartItemDto> cartItems = cartItemService.getAllCartItems();
        return cartItems;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDto> getCartItemById(@PathVariable("id") int cartItemId) {
        CartItemDto cartItem = cartItemService.getCartItemById(cartItemId);
        return new ResponseEntity<>(cartItem,HttpStatus.OK);
    }
}





