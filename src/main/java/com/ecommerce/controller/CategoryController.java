package com.ecommerce.controller;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.config.AppContants;
import com.ecommerce.model.Category;
import com.ecommerce.payload.CategoryDTO;
import com.ecommerce.payload.CategoryResponse;
import com.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final static Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;


    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getCategory(@RequestParam(name = "pageNumber", defaultValue = AppContants.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = "pageSize", defaultValue = AppContants.PAGE_SIZE, required = false) Integer pageSize,
                                                        @RequestParam(name = "sortBy", defaultValue = AppContants.SORT_CATEGORIES_BY, required = false) String sortBy, @RequestParam(name = "sortOrder", defaultValue = AppContants.SORT_DIR, required = false) String sortOrder) {
        log.info("fetching categories");
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        log.info("working inside create category");
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
        CategoryDTO deleteCategory = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(deleteCategory, HttpStatus.OK);

    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO category) {
        log.info("update category");
        CategoryDTO updatedResult = categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<>(updatedResult, HttpStatus.OK);

    }
}
