package com.api_ecommerce.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api_ecommerce.dto.request.ProductRequestDTO;
import com.api_ecommerce.dto.response.ApiResponseDTO;
import com.api_ecommerce.services.ProductService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping
    public ResponseEntity<ApiResponseDTO> getProducts(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") @Pattern(regexp = "asc|desc") String direction) {
        return ResponseEntity.ok(new ApiResponseDTO(productService.getProducts(q, minPrice, maxPrice, categoryId, page, size, sortBy, direction),HttpStatus.OK));
    }

    @GetMapping("{productId}")
    public ResponseEntity<ApiResponseDTO> getProductById(@PathVariable("productId") UUID productId){
        return ResponseEntity.ok(new ApiResponseDTO(productService.getProductById(productId),HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> saveProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO(productService.saveProduct(productRequestDTO),HttpStatus.CREATED));
    }

    @PutMapping("{productId}")
    public ResponseEntity<ApiResponseDTO> updateProduct(@PathVariable("productId") UUID productId,@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(new ApiResponseDTO(productService.updateProduct(productId,productRequestDTO),HttpStatus.OK));
    }
}
