package com.api_ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api_ecommerce.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller,Integer>{

    Boolean existsByName(String name);
    Boolean existsByUserId(UUID userId);
}
