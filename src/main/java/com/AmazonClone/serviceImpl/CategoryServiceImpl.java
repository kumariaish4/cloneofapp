package com.AmazonClone.serviceImpl;

import com.AmazonClone.entities.Category;
import com.AmazonClone.payload.CategoryDto;
import com.AmazonClone.repository.CategoryRepository;
import com.AmazonClone.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
   // private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }
        @Override
        public List<CategoryDto> getAllCategories() {
            List<Category> categories = categoryRepository.findAll();
            return categories.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }

        @Override
        public CategoryDto getCategoryById(int categoryId) {
            Category id_not_found = categoryRepository.findById(categoryId).orElseThrow(() ->
                    new EntityNotFoundException("id not found"));
            CategoryDto dto=new CategoryDto();
            dto.setId(id_not_found.getId());
            dto.setName(id_not_found.getName());
            return dto;

        }

        @Override
        public CategoryDto createCategory(CategoryDto categoryDto) {
            Category category = convertToEntity(categoryDto);
            Category savedCategory = categoryRepository.save(category);
            return convertToDto(savedCategory);
        }

        @Override
        public CategoryDto updateCategory(int categoryId, CategoryDto categoryDto) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                    new EntityNotFoundException("id not found"));
            category.setName(categoryDto.getName());
            Category updatedCategory = categoryRepository.save(category);
            return convertToDto(updatedCategory);
        }

        @Override
        public void deleteCategory(int categoryId) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                    new EntityNotFoundException("id not found"));
            categoryRepository.delete(category);
        }



        private CategoryDto convertToDto(Category category) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            return categoryDto;
        }

        private Category convertToEntity(CategoryDto categoryDto) {
            Category category = new Category();
            category.setName(categoryDto.getName());
            return category;
        }
    }
