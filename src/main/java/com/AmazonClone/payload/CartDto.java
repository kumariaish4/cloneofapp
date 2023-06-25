package com.AmazonClone.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDto {

    private int id;
    private int userId;
    private List<CartItemDto> cartItems = new ArrayList<>();
    private UserDto user;
}
