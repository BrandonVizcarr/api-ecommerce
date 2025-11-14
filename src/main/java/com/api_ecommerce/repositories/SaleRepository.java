package com.api_ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_ecommerce.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale,Long>{
    
}
