package com.api_ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api_ecommerce.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller,Integer>{
    
}
