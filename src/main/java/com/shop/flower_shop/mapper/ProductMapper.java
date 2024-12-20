package com.shop.flower_shop.mapper;

import com.shop.flower_shop.dto.ProductDto;
import com.shop.flower_shop.entity.Category;
import com.shop.flower_shop.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPurchasePrice(product.getPurchasePrice());
        dto.setSellingPrice(product.getSellingPrice());
        dto.setQuantity(product.getQuantity());
        dto.setCategoryId(product.getCategory().getId());
        return dto;
    }

    public Product toEntity(ProductDto dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPurchasePrice(dto.getPurchasePrice());
        product.setSellingPrice(dto.getSellingPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(category);
        return product;
    }
}
