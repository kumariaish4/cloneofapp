package com.AmazonClone.serviceImpl;

import com.AmazonClone.entities.Cart;
import com.AmazonClone.entities.CartItem;
import com.AmazonClone.entities.Product;
import com.AmazonClone.payload.CartDto;
import com.AmazonClone.payload.CartItemDto;
import com.AmazonClone.repository.CartItemRepository;
import com.AmazonClone.repository.CartRepository;
import com.AmazonClone.repository.ProductRepository;
import com.AmazonClone.service.CartService;
import com.AmazonClone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository,
                           ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @Override//get cart by id
    public CartDto getCartByUserId(int userId) {
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user with ID: " + userId));
        return convertToDto(cart);
    }

    @Override
    public CartDto createCart(int userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        Cart savedCart = cartRepository.save(cart);
        return convertToDto(savedCart);
    }

    @Override
    public CartDto addToCart(int cartId, CartItemDto cartItemDto) {
        Cart cart = getCartById(cartId);
        Product product = getProductById(cartItemDto.getProductId());

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemDto.getQuantity());
        //cartItem.setPrice(product.getPrice() * cartItemDto.getQuantity());
        double price = product.getPrice() * cartItemDto.getQuantity();
        cartItem.setPrice(price);

        cart.getCartItems().add(cartItem);
        cartItem.setCart(cart);

        cartRepository.save(cart);

        return convertToDto(cart);
    }

    @Override
    public CartDto updateCartItemQuantity(int cartId, int cartItemId, int quantity) {
        Cart cart = getCartById(cartId);
        CartItem cartItem = getCartItemById(cartItemId, cart);

        cartItem.setQuantity(quantity);
        cartItem.setPrice(cartItem.getProduct().getPrice() * quantity);

        cartRepository.save(cart);

        return convertToDto(cart);
    }

    @Override
    public void removeCartItem(int cartId, int cartItemId) {
        Cart cart = getCartById(cartId);
        CartItem cartItem = getCartItemById(cartItemId, cart);

        cart.getCartItems().remove(cartItem);
        cartItemRepository.deleteById(cartItemId);

        cartRepository.save(cart);
    }

    @Override
    public void clearCart(int cartId) {
        Cart cart = getCartById(cartId);

        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    private Cart getCartById(int cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with ID: " + cartId));
    }

    private Product getProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
    }

    private CartItem getCartItemById(int cartItemId, Cart cart) {
        return cart.getCartItems().stream()
                .filter(item -> item.getId() == cartItemId)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("CartItem not found with ID: " + cartItemId));
    }

    private CartDto convertToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setUserId(cart.getUserId());

        List<CartItemDto> cartItemDtos = cart.getCartItems().stream()
                .map(this::convertToCartItemDto)
                .collect(Collectors.toList());
        cartDto.setCartItems(cartItemDtos);

        return cartDto;
    }

    private CartItemDto convertToCartItemDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setProductId(cartItem.getProduct().getId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setPrice(cartItem.getPrice());

        return cartItemDto;
    }
}
