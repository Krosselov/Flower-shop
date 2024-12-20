package com.shop.flower_shop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryLogDto {
    private Long id;

    @NotNull(message = "ID продукта не может быть null")
    private Long productId;


    @Min(value = 1, message = "Количество как минимум должно быть равно 1")
    private int quantityChange;

    @NotNull(message = "Тип операции не может быть null")
    private String operationType;
    private LocalDateTime operationDateTime;
}
