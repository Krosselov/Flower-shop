package com.shop.flower_shop.mapper;

import com.shop.flower_shop.dto.SupplierDto;
import com.shop.flower_shop.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {
    public SupplierDto toDto(Supplier supplier) {
        SupplierDto dto = new SupplierDto();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setContactInfo(supplier.getContactInfo());
        return dto;
    }

    public Supplier toEntity(SupplierDto dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setContactInfo(dto.getContactInfo());
        return supplier;
    }
}