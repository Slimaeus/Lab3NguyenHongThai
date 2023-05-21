package com.example.lab3nguyenhongthai.service;

import com.example.lab3nguyenhongthai.entity.Category;
import com.example.lab3nguyenhongthai.repository.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final ICategoryRepository categoryRepository;
    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() { return categoryRepository.findAll(); }

    public Category getCategoryById(Long id) { return categoryRepository.findById(id).orElse(null); }

    public Category saveCategory(Category category) { return categoryRepository.save(category); }

    public void deleteCategory(Long id) { categoryRepository.deleteById(id); }
}
