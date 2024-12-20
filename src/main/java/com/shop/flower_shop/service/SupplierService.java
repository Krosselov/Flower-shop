package com.shop.flower_shop.service;

import com.shop.flower_shop.dto.SupplierDto;

import java.util.List;

public interface SupplierService {
    SupplierDto createSupplier(SupplierDto supplierDto);
    List<SupplierDto> getAllSuppliers();
    SupplierDto getSupplierById(Long id);
    SupplierDto updateSupplier(Long id, SupplierDto supplierDto);
    void deleteSupplier(Long id);
}
