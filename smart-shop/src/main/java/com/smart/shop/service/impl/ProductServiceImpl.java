package com.smart.shop.service.impl;

import com.smart.shop.entity.Product;
import com.smart.shop.mapper.ProductMapper;
import com.smart.shop.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String CACHE_PREFIX = "product:list:";

    private final ProductMapper productMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    public ProductServiceImpl(ProductMapper productMapper, RedisTemplate<String, Object> redisTemplate) {
        this.productMapper = productMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Cacheable(cacheNames = "product", key = "#id", unless = "#result == null")
    public Product findById(Integer id) {
        return productMapper.findById(id);
    }

    @Override
    public PageInfo<Product> findByCondition(Integer categoryId, String productName,
                                              Double minPrice, Double maxPrice,
                                              Integer pageNum, Integer pageSize) {
        String cacheKey = CACHE_PREFIX + categoryId + "_" + productName + "_" + minPrice + "_" + maxPrice + "_" + pageNum + "_" + pageSize;

        @SuppressWarnings("unchecked")
        PageInfo<Product> cached = (PageInfo<Product>) redisTemplate.opsForValue().get(cacheKey);
        if (cached != null) {
            return cached;
        }

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Product> pageInfo = new PageInfo<>(productMapper.findByCondition(categoryId, productName, minPrice, maxPrice));

        redisTemplate.opsForValue().set(cacheKey, pageInfo, 5, TimeUnit.MINUTES);
        return pageInfo;
    }

    @Override
    public void save(Product product) {
        productMapper.insert(product);
        clearProductListCache();
    }

    @Override
    @CacheEvict(cacheNames = "product", key = "#product.id")
    public void update(Product product) {
        productMapper.update(product);
        clearProductListCache();
    }

    @Override
    @CacheEvict(cacheNames = "product", key = "#id")
    public void delete(Integer id) {
        productMapper.delete(id);
        clearProductListCache();
    }

    private void clearProductListCache() {
        var keys = redisTemplate.keys(CACHE_PREFIX + "*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public int getCount() {
        return productMapper.getCount();
    }

    @Override
    public List<Product> findRecent() {
        return productMapper.findRecent();
    }

    @Override
    public List<java.util.Map<String, Object>> getMonthlyTrend() {
        return productMapper.getMonthlyTrend();
    }
}
