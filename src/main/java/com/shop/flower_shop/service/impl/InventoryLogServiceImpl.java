package com.shop.flower_shop.service.impl;

import com.shop.flower_shop.dto.InventoryLogDto;
import com.shop.flower_shop.entity.InventoryLog;
import com.shop.flower_shop.entity.Product;
import com.shop.flower_shop.mapper.InventoryLogMapper;
import com.shop.flower_shop.repository.InventoryLogRepository;
import com.shop.flower_shop.repository.ProductRepository;
import com.shop.flower_shop.service.InventoryLogService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class InventoryLogServiceImpl implements InventoryLogService {
    private InventoryLogRepository inventoryLogRepository;
    private ProductRepository productRepository;
    private InventoryLogMapper logMapper;

    @Override
    public List<InventoryLogDto> getLogs() {
        return inventoryLogRepository.findAll().stream()
                .map(logMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryLogDto saveLog(InventoryLogDto logDto) {
        Product product = productRepository.findById(logDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));
        InventoryLog log = logMapper.toEntity(logDto);
        log.setProduct(product);
        InventoryLog savedLog = inventoryLogRepository.save(log);
        return logMapper.toDto(savedLog);
    }
}
