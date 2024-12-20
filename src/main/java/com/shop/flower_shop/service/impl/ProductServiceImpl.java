package com.shop.flower_shop.service.impl;

import com.shop.flower_shop.dto.ProductDto;
import com.shop.flower_shop.entity.Category;
import com.shop.flower_shop.entity.Product;
import com.shop.flower_shop.mapper.ProductMapper;
import com.shop.flower_shop.repository.CategoryRepository;
import com.shop.flower_shop.repository.ProductRepository;
import com.shop.flower_shop.service.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);

        return products.stream()
                .map(product -> new ProductDto(product.getId(), product.getName(), product.getPurchasePrice(),
                        product.getSellingPrice(), product.getQuantity(), product.getCategory().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Категория с ID " + productDto.getCategoryId() + " не найдена"));
        Product product = productMapper.toEntity(productDto, category);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Продукт с ID " + id + " не найден"));

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Категория с ID " + productDto.getCategoryId() + " не найдена"));

        existingProduct.setName(productDto.getName());
        existingProduct.setPurchasePrice(productDto.getPurchasePrice());
        existingProduct.setSellingPrice(productDto.getSellingPrice());
        existingProduct.setQuantity(productDto.getQuantity());
        existingProduct.setCategory(category);

        Product updatedProduct = productRepository.save(existingProduct);

        return productMapper.toDto(updatedProduct);
    }


    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
