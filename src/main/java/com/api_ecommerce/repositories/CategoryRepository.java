package com.api_ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api_ecommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{
    Optional<Category> findByName(String name);
    List<Category> findAllByCanceled(Boolean canceled);
}
