package com.shop.flower_shop.service;

import com.shop.flower_shop.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long id);
    void deleteOrder(Long id);
    OrderDto updateOrder(Long id, OrderDto orderDto);
}
