package com.shop.flower_shop.controller;

import com.shop.flower_shop.dto.InventoryLogDto;
import com.shop.flower_shop.service.InventoryLogService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-logs")
@AllArgsConstructor
@NoArgsConstructor
public class InventoryLogController {
    private InventoryLogService logService;

    @GetMapping
    public ResponseEntity<List<InventoryLogDto>> getAllLogs() {
        return ResponseEntity.ok(logService.getLogs());
    }

    @PostMapping
    public ResponseEntity<InventoryLogDto> createLog(@RequestBody InventoryLogDto logDto) {
        return ResponseEntity.ok(logService.saveLog(logDto));
    }
}
