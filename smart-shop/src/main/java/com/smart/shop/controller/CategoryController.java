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
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/add")
    public String addForm() {
        return "category/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/category/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Category category) {
        categoryService.update(category);
        return "redirect:/category/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return "redirect:/category/list";
    }
}
