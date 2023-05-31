package com.example.lab3nguyenhongthai.controller.rest;

import com.example.lab3nguyenhongthai.dto.CategoryDto;
import com.example.lab3nguyenhongthai.entity.Category;
import com.example.lab3nguyenhongthai.service.CategoryService;
import com.example.lab3nguyenhongthai.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/categories")
public class ApiCategoryController {
    @Autowired
    private CategoryService categoryService;

    private CategoryDto convertToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    @GetMapping
    @ResponseBody
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories().stream()
                .map(this::convertToCategoryDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return convertToCategoryDto(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteCategory(@PathVariable Long id) {
        if (categoryService.getCategoryById(id) != null)
            categoryService.deleteCategory(id);
    }
}
