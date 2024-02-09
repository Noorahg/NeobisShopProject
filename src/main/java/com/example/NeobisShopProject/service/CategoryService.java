package com.example.NeobisShopProject.service;

import com.example.NeobisShopProject.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long categoryId);
    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);
    void deleteCategory(Long categoryId);
}
