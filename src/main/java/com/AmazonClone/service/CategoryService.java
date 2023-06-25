package com.AmazonClone.service;


import com.AmazonClone.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
        List<CategoryDto> getAllCategories();
        CategoryDto getCategoryById(int categoryId);
        CategoryDto createCategory(CategoryDto categoryDto);
        CategoryDto updateCategory(int categoryId, CategoryDto categoryDto);
        void deleteCategory(int categoryId);
    }


