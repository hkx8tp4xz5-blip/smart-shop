package com.smart.shop.service.impl;

import com.smart.shop.entity.Category;
import com.smart.shop.mapper.CategoryMapper;
import com.smart.shop.service.CategoryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CACHE_KEY = "category:list";

    private final CategoryMapper categoryMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    public CategoryServiceImpl(CategoryMapper categoryMapper, RedisTemplate<String, Object> redisTemplate) {
        this.categoryMapper = categoryMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Category> findAll() {
        @SuppressWarnings("unchecked")
        List<Category> cached = (List<Category>) redisTemplate.opsForValue().get(CACHE_KEY);
        if (cached != null) {
            return cached;
        }
        List<Category> list = categoryMapper.findAll();
        redisTemplate.opsForValue().set(CACHE_KEY, list, 10, TimeUnit.MINUTES);
        return list;
    }

    @Override
    @Cacheable(cacheNames = "category", key = "#id", unless = "#result == null")
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryMapper.insert(category);
        clearCategoryCache();
    }

    @Override
    @CacheEvict(cacheNames = "category", key = "#category.id")
    public void update(Category category) {
        categoryMapper.update(category);
        clearCategoryCache();
    }

    @Override
    @CacheEvict(cacheNames = "category", key = "#id")
    public void delete(Integer id) {
        categoryMapper.delete(id);
        clearCategoryCache();
    }

    private void clearCategoryCache() {
        redisTemplate.delete(CACHE_KEY);
    }

    @Override
    public int getCount() {
        return categoryMapper.getCount();
    }

    @Override
    public List<java.util.Map<String, Object>> getCategoryProductStats() {
        return categoryMapper.getCategoryProductStats();
    }
}
