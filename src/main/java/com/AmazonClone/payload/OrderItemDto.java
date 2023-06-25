package com.AmazonClone.payload;

import lombok.Data;

@Data
public class OrderItemDto {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private double price;

}
