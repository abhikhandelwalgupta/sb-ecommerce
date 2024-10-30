package com.ecommerce.service.impl;

import com.ecommerce.exceptions.ApiException;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.repositories.CategoryRepository;
import com.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {

        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) throw   new ApiException("No category created till now");
        return categoryList;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryNameContainingIgnoreCase(category.getCategoryName());
        if (savedCategory != null) {
            throw new ApiException("Category with the name " + category.getCategoryName() + " already exists !!");
        }
        category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(category);
        return "category id" + categoryId + "is deleted successfully!";
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(()->  new ResourceNotFoundException("Category", "categoryId", categoryId));
        category.setCategoryId(categoryId);
        return categoryRepository.save(category);



    }


}
