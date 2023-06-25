package com.AmazonClone.serviceImpl;

import com.AmazonClone.entities.CartItem;
import com.AmazonClone.payload.CartItemDto;
import com.AmazonClone.payload.ProductDto;
import com.AmazonClone.repository.CartItemRepository;
import com.AmazonClone.service.CartItemService;
import com.AmazonClone.service.ProductService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    @Override
    public CartItemDto createCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setProductId(cartItemDto.getProductId());
        cartItem.setCartId(cartItemDto.getCartId());


        // Fetch the product to calculate the price
        ProductDto product = productService.getProductById(cartItemDto.getProductId());

        if (product == null) {
            throw new EntityNotFoundException("Product not found with ID: " + cartItemDto.getProductId());
        }

        double price = product.getPrice() * cartItemDto.getQuantity();
        cartItem.setPrice(price);

        CartItem savedCartItem = cartItemRepository.save(cartItem);

        return convertToDto(savedCartItem);
    }

    @Override
    public CartItemDto updateCartItem(int cartItemId, CartItemDto cartItemDto) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);

        if (!cartItemOptional.isPresent()) {
            throw new EntityNotFoundException("CartItem not found with ID: " + cartItemId);
        }

        CartItem cartItem = cartItemOptional.get();
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setProductId(cartItemDto.getProductId());
        cartItem.setCartId(cartItemDto.getCartId());
        // Fetch the product to calculate the price
        ProductDto product = productService.getProductById(cartItemDto.getProductId());

        if (product == null) {
            throw new EntityNotFoundException("Product not found with ID: " + cartItemDto.getProductId());
        }

        double price = product.getPrice() * cartItemDto.getQuantity();
        cartItem.setPrice(price);

        CartItem updatedCartItem = cartItemRepository.save(cartItem);

        return convertToDto(updatedCartItem);
    }

    @Override
    public void deleteCartItem(int cartItemId) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);

        if (!cartItemOptional.isPresent()) {
            throw new EntityNotFoundException("CartItem not found with ID: " + cartItemId);
        }

        CartItem cartItem = cartItemOptional.get();
        cartItemRepository.delete(cartItem);
    }

    @Override
    public List<CartItemDto> getAllCartItems() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItems.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartItemDto getCartItemById(int cartItemId) {
        CartItem cartItem = getCartItem(cartItemId);
        return convertToDto(cartItem);
    }

    private CartItem getCartItem(int cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("CartItem not found with ID: " + cartItemId));
    }

    private CartItemDto convertToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setCartId(cartItem.getCartId());

        return cartItemDto;
    }
}
