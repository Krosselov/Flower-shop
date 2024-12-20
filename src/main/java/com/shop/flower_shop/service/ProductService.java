package com.shop.flower_shop.service;

import com.shop.flower_shop.dto.ProductDto;

import java.util.List;


public interface ProductService {

    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto saveProduct(ProductDto productDto);
    void deleteProduct(Long id);
    ProductDto updateProduct(Long id, ProductDto productDto);
    List<ProductDto> getProductsByCategory(Long categoryId);
}
