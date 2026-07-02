package com.smart.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.smart.shop.mapper")
@EnableCaching
public class SmartShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartShopApplication.class, args);
    }
}
