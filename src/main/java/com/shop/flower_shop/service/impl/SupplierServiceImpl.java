package com.shop.flower_shop.service.impl;

import com.shop.flower_shop.dto.SupplierDto;
import com.shop.flower_shop.entity.Supplier;
import com.shop.flower_shop.mapper.SupplierMapper;
import com.shop.flower_shop.repository.SupplierRepository;
import com.shop.flower_shop.service.SupplierService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private SupplierMapper supplierMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public SupplierDto createSupplier(SupplierDto supplierDto) {
        Supplier supplier = supplierMapper.toEntity(supplierDto);
        supplierRepository.save(supplier);
        return supplierMapper.toDto(supplier);
    }

    @Override
    public List<SupplierDto> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDto getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        return supplierMapper.toDto(supplier);
    }

    @Override
    public SupplierDto updateSupplier(Long id, SupplierDto supplierDto) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        existingSupplier.setName(supplierDto.getName());
        existingSupplier.setContactInfo(supplierDto.getContactInfo());
        supplierRepository.save(existingSupplier);
        return supplierMapper.toDto(existingSupplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        supplierRepository.delete(supplier);
    }
}
