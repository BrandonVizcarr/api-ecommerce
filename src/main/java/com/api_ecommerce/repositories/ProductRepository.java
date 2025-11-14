package com.api_ecommerce.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import com.api_ecommerce.entities.Product;
import jakarta.persistence.LockModeType;

public interface ProductRepository extends JpaRepository<Product,UUID>, JpaSpecificationExecutor<Product>{
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findByProductId(UUID productId);
}
