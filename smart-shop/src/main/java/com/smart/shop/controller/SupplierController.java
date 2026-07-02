package com.smart.shop.controller;

import com.smart.shop.entity.Supplier;
import com.smart.shop.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Supplier> suppliers = supplierService.findAll();
        model.addAttribute("suppliers", suppliers);
        return "supplier/list";
    }

    @GetMapping("/add")
    public String addForm() {
        return "supplier/add";
    }

    @PostMapping("/add")
    public String add(Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/supplier/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Supplier supplier = supplierService.findById(id);
        if (supplier == null) {
            return "redirect:/supplier/list";
        }
        model.addAttribute("supplier", supplier);
        return "supplier/edit";
    }

    @PostMapping("/edit")
    public String edit(Supplier supplier) {
        supplierService.update(supplier);
        return "redirect:/supplier/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        supplierService.delete(id);
        return "redirect:/supplier/list";
    }
}
