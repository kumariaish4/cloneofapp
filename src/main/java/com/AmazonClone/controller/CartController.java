package com.AmazonClone.controller;

import com.AmazonClone.payload.CartDto;
import com.AmazonClone.payload.CartItemDto;
import com.AmazonClone.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable int userId) {
        CartDto cartDto = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> createCart(@PathVariable int userId) {
        CartDto cartDto = cartService.createCart(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartDto> addToCart(@PathVariable int cartId, @RequestBody CartItemDto cartItemDto) {
        CartDto cartDto = cartService.addToCart(cartId, cartItemDto);
        return new ResponseEntity<>(cartDto,HttpStatus.CREATED);
    }

    @PutMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<CartDto> updateCartItemQuantity(@PathVariable int cartId, @PathVariable int cartItemId,
                                                          @RequestParam int quantity) {
        CartDto cartDto = cartService.updateCartItemQuantity(cartId, cartItemId, quantity);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/items/{cartItemId}")
    public void removeCartItem(@PathVariable int cartId, @PathVariable int cartItemId) {
        cartService.removeCartItem(cartId, cartItemId);

    }

    @DeleteMapping("/{cartId}")
    public void clearCart(@PathVariable int cartId) {
        cartService.clearCart(cartId);

    }
}

