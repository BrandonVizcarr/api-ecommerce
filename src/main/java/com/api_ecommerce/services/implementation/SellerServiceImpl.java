package com.api_ecommerce.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_ecommerce.dto.request.SellerRequestDTO;
import com.api_ecommerce.entities.Seller;
import com.api_ecommerce.repositories.SellerRepository;
import com.api_ecommerce.services.SellerService;

@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerRepository sellerRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean saveSeller(SellerRequestDTO sellerRequestDTO) {
        try {
            Seller seller = modelMapper.map(sellerRequestDTO, Seller.class);
            return sellerRepository.save(seller).getSellerId() != null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Seller getSellerById(Integer sellerId) {
        return sellerRepository.findById(sellerId).orElseThrow(()-> new RuntimeException("Product not found: "+sellerId) );
    }
    
}
