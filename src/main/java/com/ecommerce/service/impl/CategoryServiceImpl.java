package com.ecommerce.service.impl;

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
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Record not found"));
        categoryRepository.delete(category);
        return "category id" + categoryId + "is deleted successfully!";
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found"));
        category.setCategoryId(categoryId);
        return categoryRepository.save(category);



    }


}
