package com.api_ecommerce.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.api_ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product,UUID>, JpaSpecificationExecutor<Product>{

}
