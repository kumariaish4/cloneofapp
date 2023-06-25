package com.AmazonClone.service;

import com.AmazonClone.payload.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto getProductById(int id);
    List<ProductDto> getAllProducts();
    ProductDto updateProduct(int id, ProductDto productDto);
    void deleteProduct(int id);
}

