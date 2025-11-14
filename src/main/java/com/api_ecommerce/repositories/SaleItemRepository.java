package com.api_ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_ecommerce.entities.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem,Long>{
 
}
