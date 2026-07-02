package com.smart.shop.service.impl;

import com.smart.shop.entity.Supplier;
import com.smart.shop.mapper.SupplierMapper;
import com.smart.shop.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierMapper supplierMapper) {
        this.supplierMapper = supplierMapper;
    }

    @Override
    public List<Supplier> findAll() {
        return supplierMapper.findAll();
    }

    @Override
    public Supplier findById(Integer id) {
        return supplierMapper.findById(id);
    }

    @Override
    public void save(Supplier supplier) {
        supplierMapper.insert(supplier);
    }

    @Override
    public void update(Supplier supplier) {
        supplierMapper.update(supplier);
    }

    @Override
    public void delete(Integer id) {
        supplierMapper.delete(id);
    }
}
