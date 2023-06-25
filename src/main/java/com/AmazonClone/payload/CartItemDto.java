package com.AmazonClone.payload;

import lombok.Data;

@Data
public class CartItemDto {
    private int id;
    private int cartId;
    private int productId;
    private int quantity;
    private double price;
}
