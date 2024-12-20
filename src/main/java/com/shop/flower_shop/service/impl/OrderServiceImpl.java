package com.shop.flower_shop.service.impl;

import com.shop.flower_shop.dto.OrderDto;
import com.shop.flower_shop.entity.Order;
import com.shop.flower_shop.entity.Product;
import com.shop.flower_shop.entity.Supplier;
import com.shop.flower_shop.mapper.OrderMapper;
import com.shop.flower_shop.repository.OrderRepository;
import com.shop.flower_shop.repository.ProductRepository;
import com.shop.flower_shop.repository.SupplierRepository;
import com.shop.flower_shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private SupplierRepository supplierRepository;
    private OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository,
                            SupplierRepository supplierRepository,
                            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Supplier supplier = supplierRepository.findById(orderDto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = orderMapper.toEntity(orderDto);
        order.setSupplier(supplier);
        order.setProduct(product);

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Supplier supplier = supplierRepository.findById(orderDto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingOrder.setOrderDate(orderDto.getOrderDate());
        existingOrder.setQuantity(orderDto.getQuantity());
        existingOrder.setSupplier(supplier);
        existingOrder.setProduct(product);

        Order updatedOrder = orderRepository.save(existingOrder);

        return orderMapper.toDto(updatedOrder);
    }
}
