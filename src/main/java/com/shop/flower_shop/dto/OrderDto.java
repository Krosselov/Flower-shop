package com.shop.flower_shop.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {
    private Long id;
    private Long supplierId;
    private Long productId;
    private int quantity;
    private LocalDate orderDate;
}