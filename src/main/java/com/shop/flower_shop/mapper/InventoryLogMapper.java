package com.shop.flower_shop.mapper;

import com.shop.flower_shop.dto.InventoryLogDto;
import com.shop.flower_shop.entity.InventoryLog;
import com.shop.flower_shop.entity.OperationType;
import org.springframework.stereotype.Component;

@Component
public class InventoryLogMapper {

    public InventoryLogDto toDto(InventoryLog inventoryLog) {
        InventoryLogDto dto = new InventoryLogDto();
        dto.setId(inventoryLog.getId());
        dto.setProductId(inventoryLog.getProduct().getId());
        dto.setQuantityChange(inventoryLog.getQuantityChange());
        dto.setOperationType(inventoryLog.getOperationType().name());
        dto.setOperationDateTime(inventoryLog.getOperationDateTime());
        return dto;
    }

    public InventoryLog toEntity(InventoryLogDto inventoryLogDto) {
        InventoryLog inventoryLog = new InventoryLog();
        inventoryLog.setId(inventoryLogDto.getId());
        inventoryLog.setQuantityChange(inventoryLogDto.getQuantityChange());
        inventoryLog.setOperationType(Enum.valueOf(OperationType.class, inventoryLogDto.getOperationType()));
        return inventoryLog;
    }
}
