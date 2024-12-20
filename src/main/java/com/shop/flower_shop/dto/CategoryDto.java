package com.shop.flower_shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    @NotBlank(message = "Название категории не может быть пустым")
    @Size(min = 2, max = 100, message = "Название категории должно быть от 2 до 100 символов")
    private String name;
}