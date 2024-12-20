package com.shop.flower_shop.controller;

import com.shop.flower_shop.dto.CategoryDto;
import com.shop.flower_shop.service.CategoryService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
@NoArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllCategories(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categoryList";
    }

    @GetMapping("/create")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new CategoryDto());
        return "categoryForm";
    }

    @PostMapping("/create")
    public String saveCategory(@ModelAttribute("category") @Valid CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        CategoryDto category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "categoryForm";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute("category") @Valid CategoryDto categoryDto) {
        categoryService.updateCategory(id, categoryDto);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}