package com.smart.shop.controller;

import com.github.pagehelper.PageInfo;
import com.smart.shop.entity.Category;
import com.smart.shop.entity.Product;
import com.smart.shop.service.CategoryService;
import com.smart.shop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false) Integer categoryId,
                       @RequestParam(required = false) String productName,
                       @RequestParam(required = false) Double minPrice,
                       @RequestParam(required = false) Double maxPrice,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        PageInfo<Product> pageInfo = productService.findByCondition(
                categoryId, productName, minPrice, maxPrice, pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);

        model.addAttribute("categoryId", categoryId);
        model.addAttribute("productName", productName);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        return "product/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "product/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "product/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Product product) {
        productService.update(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/product/list";
    }
}
