package com.smart.shop.service;

import com.smart.shop.entity.Product;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface ProductService {
    Product findById(Integer id);
    PageInfo<Product> findByCondition(Integer categoryId, String productName,
                                      Double minPrice, Double maxPrice,
                                      Integer pageNum, Integer pageSize);
    void save(Product product);
    void update(Product product);
    void delete(Integer id);
    int getCount();
    List<Product> findRecent();
    List<java.util.Map<String, Object>> getMonthlyTrend();
}
