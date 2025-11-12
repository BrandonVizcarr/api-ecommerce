package com.api_ecommerce.services;

import org.springframework.data.domain.Page;
import com.api_ecommerce.dto.request.SellerRequestDTO;
import com.api_ecommerce.entities.Seller;

public interface SellerService {
    public Seller saveSeller(SellerRequestDTO sellerRequestDTO);
    public Seller getSellerById(Integer sellerId);
    public Page<Seller> getSellers(int page, int size,  String sortBy, String direction);
    public Seller updateSeller(Integer sellerId, SellerRequestDTO sellerRequestDTO);
}
