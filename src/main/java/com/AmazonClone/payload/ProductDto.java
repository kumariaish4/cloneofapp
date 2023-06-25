package com.AmazonClone.payload;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private int id;
    private String title;
    private String description;
    private double price;
    private int quantity;
    private String imageURL;
    private List<Integer> categoryIds;
}
