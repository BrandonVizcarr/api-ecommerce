package com.api_ecommerce.services;

import java.util.List;

import com.api_ecommerce.dto.request.CategoryRequestDTO;
import com.api_ecommerce.entities.Category;

public interface CategoryService {

    public Category saveCategory(CategoryRequestDTO categoryRequestDTO);
    public List<Category> getCategories();
    public Boolean deleteCategory(Integer categoryId);
}
