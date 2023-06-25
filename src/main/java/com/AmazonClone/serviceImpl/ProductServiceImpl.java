package com.AmazonClone.serviceImpl;

import com.AmazonClone.entities.Category;
import com.AmazonClone.entities.Product;
import com.AmazonClone.payload.ProductDto;
import com.AmazonClone.repository.CategoryRepository;
import com.AmazonClone.repository.ProductRepository;
import com.AmazonClone.service.ProductService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(int productId) {
        Product id_not_found = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("id not found"));
    ProductDto dto=new ProductDto();
    dto.setId(id_not_found.getId());
    dto.setDescription(id_not_found.getDescription());
    dto.setImageURL(id_not_found.getImageURL());
    dto.setPrice(id_not_found.getPrice());
    dto.setQuantity(id_not_found.getQuantity());
    dto.setTitle(id_not_found.getTitle());
    dto.setCategoryIds(getCategoryIds(id_not_found.getCategories()));
    return dto;

    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = convertToEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return convertToDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(int productId, ProductDto productDto) {
        Product product =  productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("id not found"));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setImageURL(productDto.getImageURL());
        product.setCategories(getCategories(productDto.getCategoryIds()));
        Product updatedProduct = productRepository.save(product);
        return convertToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(int productId) {
        Product id_not_found = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("id not found"));
        productRepository.delete(id_not_found);
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setImageURL(product.getImageURL());
        productDto.setCategoryIds(getCategoryIds(product.getCategories()));
        return productDto;
    }

    private Product convertToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setImageURL(productDto.getImageURL());
        product.setCategories(getCategories(productDto.getCategoryIds()));
        return product;
    }



    private List<Integer> getCategoryIds(List<Category> categories) {
        return categories.stream()
                .map(Category::getId)
                .collect(Collectors.toList());
    }

    private List<Category> getCategories(List<Integer> categoryIds) {
        return categoryRepository.findAllById(categoryIds);
    }
}
