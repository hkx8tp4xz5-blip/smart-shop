package com.smart.shop.service;

import com.smart.shop.entity.Supplier;
import java.util.List;

public interface SupplierService {
    List<Supplier> findAll();
    Supplier findById(Integer id);
    void save(Supplier supplier);
    void update(Supplier supplier);
    void delete(Integer id);
}
