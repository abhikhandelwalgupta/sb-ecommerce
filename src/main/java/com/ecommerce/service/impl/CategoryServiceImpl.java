package com.ecommerce.service.impl;

import com.ecommerce.exceptions.ApiException;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.payload.CategoryDTO;
import com.ecommerce.payload.CategoryResponse;
import com.ecommerce.repositories.CategoryRepository;
import com.ecommerce.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    private Long nextId = 1L;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) throws ApiException {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize,sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
        List<Category> categoryList = categoryPage.getContent();
        if (categoryList.isEmpty()) throw new ApiException("No category created till now");
        List<CategoryDTO> categoryDTOS = categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setLastPage(categoryPage.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category findCategory = categoryRepository.findByCategoryNameContainingIgnoreCase(categoryDTO.getCategoryName());
        if (findCategory != null)
            throw new ApiException("Category with the name " + categoryDTO.getCategoryName() + " already exists !!");

        categoryDTO.setCategoryId(nextId++);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category findCategory = categoryRepository.findByCategoryNameContainingIgnoreCase(categoryDTO.getCategoryName());
        if (findCategory != null)
            throw new ApiException("Category with the name " + categoryDTO.getCategoryName() + " already exists !!");
        categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryDTO.setCategoryId(categoryId);
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
}
