package com.api_ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api_ecommerce.dto.request.SellerRequestDTO;
import com.api_ecommerce.dto.response.ApiResponseDTO;
import com.api_ecommerce.services.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sellers")
@RequiredArgsConstructor
public class SellerController {

    
    private final SellerService sellerService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO> saveSeller(@RequestBody SellerRequestDTO sellerRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO(sellerService.saveSeller(sellerRequestDTO),HttpStatus.CREATED));
    }

    @GetMapping("{sellerId}")
    public ResponseEntity<ApiResponseDTO> getSellerById(@PathVariable("sellerId") Integer sellerId){
        return ResponseEntity.ok(new ApiResponseDTO(sellerService.getSellerById(sellerId),HttpStatus.OK));
    }

}
