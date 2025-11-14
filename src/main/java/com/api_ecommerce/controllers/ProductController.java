package com.api_ecommerce.controllers;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api_ecommerce.dto.request.ProductRequestDTO;
import com.api_ecommerce.dto.request.RateRequestDTO;
import com.api_ecommerce.dto.response.ApiResponseDTO;
import com.api_ecommerce.services.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    
    @GetMapping
    public ResponseEntity<ApiResponseDTO> getProducts(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") @Pattern(regexp = "asc|desc") String direction,
            @RequestParam(defaultValue = "false") Boolean canceled) {
        return ResponseEntity.ok(new ApiResponseDTO(productService.getProducts(q, minPrice, maxPrice, categoryId, canceled, page, size, sortBy, direction),HttpStatus.OK));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponseDTO> getProductById(@PathVariable("productId") UUID productId){
        return ResponseEntity.ok(new ApiResponseDTO(productService.getProductById(productId),HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> saveProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO(productService.saveProduct(productRequestDTO),HttpStatus.CREATED));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponseDTO> updateProduct(@PathVariable("productId") UUID productId,@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(new ApiResponseDTO(productService.updateProduct(productId,productRequestDTO),HttpStatus.OK));
    }

    @GetMapping("{productId}/reviews")
    public ResponseEntity<ApiResponseDTO> getProductReviews(@PathVariable("productId") UUID productId,@RequestParam(defaultValue = "0") @Min(0) int page,@RequestParam(defaultValue = "10") @Min(1) @Max(100) int size){
        return ResponseEntity.ok(new ApiResponseDTO(productService.getReviwsByProductId(productId, page, size),HttpStatus.OK));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseDTO> deleteProduct(@PathVariable("productId") UUID productId){
        return ResponseEntity.ok(new ApiResponseDTO(productService.deleteProductById(productId),HttpStatus.OK));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ApiResponseDTO> pacthProductRate(@PathVariable("productId") UUID productId,@Valid @RequestBody RateRequestDTO rate){
        return ResponseEntity.ok(new ApiResponseDTO(productService.patchProductRate(productId, rate),HttpStatus.OK));
    }
    
}

