package com.shop.flower_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private Double purchasePrice;
    private Double sellingPrice;
    private Integer quantity;
    private Long categoryId;
}
