package com.example.NeobisShopProject.controller;

import com.example.NeobisShopProject.dto.CategoryDto;
import com.example.NeobisShopProject.service.Impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Tag(name = "Category", description = "Controller for category objects")

@RestController
@RequestMapping("/api/categories")
    public class CategoryController {

        private final CategoryServiceImpl categoryServiceImpl;
@Autowired
    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }


    @PostMapping
        public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
            CategoryDto createdCategory = categoryServiceImpl.createCategory(categoryDto);
            return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<CategoryDto>> getAllCategories() {
            List<CategoryDto> categories = categoryServiceImpl.getAllCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }

        @GetMapping("/{categoryId}")
        public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId) {
            CategoryDto category = categoryServiceImpl.getCategoryById(categoryId);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }

        @PutMapping("/{categoryId}")
        public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto) {
            CategoryDto updatedCategory = categoryServiceImpl.updateCategory(categoryId, categoryDto);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        }

        @DeleteMapping("/{categoryId}")
        public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
            categoryServiceImpl.deleteCategory(categoryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
