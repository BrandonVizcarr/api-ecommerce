package com.api_ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api_ecommerce.dto.request.RateRequestDTO;
import com.api_ecommerce.dto.request.SellerRequestDTO;
import com.api_ecommerce.dto.response.ApiResponseDTO;
import com.api_ecommerce.services.SellerService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
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

    @GetMapping("/{sellerId}")
    public ResponseEntity<ApiResponseDTO> getSellerById(@PathVariable("sellerId") Long sellerId){
        return ResponseEntity.ok(new ApiResponseDTO(sellerService.getSellerById(sellerId),HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO> getSellers(@RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(0) int size, @RequestParam(defaultValue = "rate") String sortBy,
            @RequestParam(defaultValue = "asc") @Pattern(regexp = "asc|desc") String direction) {
        return ResponseEntity
                .ok(new ApiResponseDTO(sellerService.getSellers(page, size, sortBy, direction), HttpStatus.OK));
    }
    
    @DeleteMapping("/{sellerId}")
    public ResponseEntity<ApiResponseDTO> deleteSeller(@PathVariable("sellerId") Long sellerId){
        return ResponseEntity.ok(new ApiResponseDTO(sellerService.deleteSeller(sellerId),HttpStatus.OK));
    }

    @PatchMapping("/{sellerId}")
    public ResponseEntity<ApiResponseDTO> patchSeller(@PathVariable("sellerId") Long sellerId, @RequestBody RateRequestDTO rateRequestDTO){
        return ResponseEntity.ok(new ApiResponseDTO(sellerService.patchSeller(sellerId, rateRequestDTO),HttpStatus.OK));
    }

}
