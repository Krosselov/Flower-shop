package com.shop.flower_shop.mapper;

import com.shop.flower_shop.dto.OrderDto;
import com.shop.flower_shop.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setSupplierId(order.getSupplier().getId());
        dto.setProductId(order.getProduct().getId());
        dto.setQuantity(order.getQuantity());
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }

    public Order toEntity(OrderDto dto) {
        Order order = new Order();
        order.setQuantity(dto.getQuantity());
        order.setOrderDate(dto.getOrderDate());
        return order;
    }
}