package com.smart.shop.controller;

import com.smart.shop.entity.Product;
import com.smart.shop.service.CategoryService;
import com.smart.shop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public IndexController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String landing() {
        return "landing";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        int productCount = productService.getCount();
        int categoryCount = categoryService.getCount();
        List<Product> recentProducts = productService.findRecent();
        List<Map<String, Object>> monthlyTrend = productService.getMonthlyTrend();
        long maxMonth = 1;
        for (Map<String, Object> m : monthlyTrend) {
            long count = ((Number) m.get("monthCount")).longValue();
            if (count > maxMonth) maxMonth = count;
        }
        List<Map<String, Object>> categoryStats = categoryService.getCategoryProductStats();

        model.addAttribute("productCount", productCount);
        model.addAttribute("categoryCount", categoryCount);
        model.addAttribute("recentProducts", recentProducts);
        model.addAttribute("monthlyTrend", monthlyTrend);
        model.addAttribute("maxMonth", maxMonth);
        model.addAttribute("categoryStats", categoryStats);
        return "welcome";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "error/403";
    }
}
