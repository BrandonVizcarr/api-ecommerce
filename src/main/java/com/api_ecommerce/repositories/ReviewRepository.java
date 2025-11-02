package com.api_ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api_ecommerce.entities.Review;

public interface ReviewRepository extends JpaRepository<Review,Long>{

    Page<Review> findByProductId(UUID productId,Pageable pageable);
    

}
