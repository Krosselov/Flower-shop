package com.shop.flower_shop.service;

import com.shop.flower_shop.dto.InventoryLogDto;

import java.util.List;

public interface InventoryLogService {
    List<InventoryLogDto> getLogs();
    InventoryLogDto saveLog(InventoryLogDto logDto);
}
