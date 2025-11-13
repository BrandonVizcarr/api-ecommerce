package com.api_ecommerce.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api_ecommerce.dto.request.CategoryRequestDTO;
import com.api_ecommerce.entities.Category;
import com.api_ecommerce.repositories.CategoryRepository;
import com.api_ecommerce.services.CategoryService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Category saveCategory(CategoryRequestDTO categoryRequestDTO) {
        categoryRepository.findByName(categoryRequestDTO.getName()).ifPresent(
                category -> {
                    if (category.getName().equalsIgnoreCase(categoryRequestDTO.getName())) {
                        throw new EntityExistsException("Category with this name already exists");
                    }
                });
        Category category = modelMapper.map(categoryRequestDTO, Category.class);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAllParentCategories();
    }

    @Override
    public Boolean deleteCategory(Integer categoryId) {
        return categoryRepository.findById(categoryId).map
        (
            existing->{
                existing.setCanceled(true);
                return categoryRepository.save(existing).getCanceled();
            }
        ).orElseThrow(()-> new EntityNotFoundException("Category not found: "+categoryId));
    }
    
    
}

