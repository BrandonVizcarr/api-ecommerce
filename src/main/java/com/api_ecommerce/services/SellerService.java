package com.api_ecommerce.services;

import org.springframework.data.domain.Page;
import com.api_ecommerce.dto.request.RateRequestDTO;
import com.api_ecommerce.dto.request.SellerRequestDTO;
import com.api_ecommerce.entities.Seller;

public interface SellerService {
    public Seller saveSeller(SellerRequestDTO sellerRequestDTO);
    public Seller getSellerById(Long sellerId);
    public Page<Seller> getSellers(int page, int size,  String sortBy, String direction);
    public Seller updateSeller(Long sellerId, SellerRequestDTO sellerRequestDTO);
    public Seller patchSeller(Long sellerId,RateRequestDTO rateRequestDTO);
    public Boolean deleteSeller(Long sellerId);
}
