package com.shop.flower_shop.controller;

import com.shop.flower_shop.dto.CategoryDto;
import com.shop.flower_shop.dto.ProductDto;
import com.shop.flower_shop.entity.Category;
import com.shop.flower_shop.entity.Product;
import com.shop.flower_shop.service.CategoryService;
import com.shop.flower_shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
@NoArgsConstructor
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String getAllProducts(@RequestParam(required = false) Long categoryId, Model model) {
        List<ProductDto> products;
        if (categoryId != null) {
            products = productService.getProductsByCategory(categoryId);
        } else {
            products = productService.getAllProducts();
        }

        List<CategoryDto> categories = categoryService.getAllCategories();

        double totalIncome = 0.0;
        int totalQuantity = 0;

        Map<Long, String> categoryMap = categories.stream()
                .collect(Collectors.toMap(CategoryDto::getId, CategoryDto::getName));

        for (ProductDto product : products) {
            totalIncome += (product.getSellingPrice() - product.getPurchasePrice()) * product.getQuantity();
            totalQuantity += product.getQuantity();
        }

        model.addAttribute("products", products);
        model.addAttribute("categoryMap", categoryMap);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", categoryId);

        return "productList";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        ProductDto product = productService.getProductById(id);
        List<CategoryDto> categories = categoryService.getAllCategories();

        Map<Long, String> categoryMap = categories.stream()
                .collect(Collectors.toMap(CategoryDto::getId, CategoryDto::getName));

        model.addAttribute("product", product);
        model.addAttribute("categoryMap", categoryMap);

        return "productDetail";
    }


    @GetMapping("/create")
    public String createProductForm(Model model) {
        model.addAttribute("product", new ProductDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "productForm";
    }

    @PostMapping("/create")
    public String saveProduct(@ModelAttribute("product") @Valid ProductDto productDto) {
        productService.saveProduct(productDto);
        return "redirect:/products";
    }


    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        ProductDto product = productService.getProductById(id);
        model.addAttribute("product", product);
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "productForm";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") @Valid ProductDto productDto) {
        productService.updateProduct(id, productDto);
        return "redirect:/products";
    }

    @GetMapping("/filter/{id}")
    public String getProductsByCategory(@PathVariable Long id, Model model) {
        List<ProductDto> products = productService.getProductsByCategory(id);
        List<CategoryDto> categories = categoryService.getAllCategories();
        double totalIncome = 0.0;
        int totalQuantity = 0;
        Map<Long, String> categoryMap = categories.stream()
                .collect(Collectors.toMap(CategoryDto::getId, CategoryDto::getName));
        model.addAttribute("products", products);
        model.addAttribute("categoryMap", categoryMap);
        model.addAttribute("categories", categories);
        for (ProductDto product : products) {
            totalIncome += (product.getSellingPrice() - product.getPurchasePrice()) * product.getQuantity();
            totalQuantity += product.getQuantity();
        }
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalQuantity", totalQuantity);
        return "productList";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}