package com.smart.shop.service;

import com.smart.shop.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Integer id);
    void save(Category category);
    void update(Category category);
    void delete(Integer id);
    int getCount();
    List<java.util.Map<String, Object>> getCategoryProductStats();
}
