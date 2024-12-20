package com.shop.flower_shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название не может быть пустым")
    private String name;

    @NotNull(message = "Цена закупки не может быть пустой")
    @Min(value = 0, message = "Цена закупки должна быть неотрицательной")
    private Double purchasePrice;

    @NotNull(message = "Цена продажи не может быть пустой")
    @Min(value = 0, message = "Цена продажи должна быть неотрицательной")
    private Double sellingPrice;

    @NotNull(message = "Количество не может быть пустым")
    @Min(value = 0, message = "Количество должно быть неотрицательным")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
