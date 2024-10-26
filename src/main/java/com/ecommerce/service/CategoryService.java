package com.ecommerce.service;

import com.ecommerce.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long id);
    Category updateCategory(Long categoryId ,Category category);
}
