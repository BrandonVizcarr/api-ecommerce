package com.api_ecommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_ecommerce.dto.request.SaleRequestDTO;
import com.api_ecommerce.dto.response.ApiResponseDTO;
import com.api_ecommerce.services.SaleService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
    
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO> saveSale(@RequestBody SaleRequestDTO saleRequestDTO) {
       return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO(saleService.saveSale(saleRequestDTO),HttpStatus.CREATED));
    }
    
}
