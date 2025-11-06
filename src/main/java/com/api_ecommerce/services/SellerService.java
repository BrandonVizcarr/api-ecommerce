package com.api_ecommerce.services;

import com.api_ecommerce.dto.request.SellerRequestDTO;
import com.api_ecommerce.entities.Seller;

public interface SellerService {
    public Seller saveSeller(SellerRequestDTO sellerRequestDTO);
    public Seller getSellerById(Integer sellerId);
}
