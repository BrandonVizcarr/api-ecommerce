package com.api_ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_ecommerce.dto.request.CategoryRequestDTO;
import com.api_ecommerce.dto.response.ApiResponseDTO;
import com.api_ecommerce.services.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO> getCategories(){
        return ResponseEntity.ok(new ApiResponseDTO(categoryService.getCategories(),HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> saveCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDTO(categoryService.saveCategory(categoryRequestDTO), HttpStatus.CREATED));
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseDTO> deleteCategory(@PathVariable("categoryId") Integer categoryId){
        return ResponseEntity.ok(new ApiResponseDTO(categoryService.deleteCategory(categoryId),HttpStatus.OK));
    }

}
