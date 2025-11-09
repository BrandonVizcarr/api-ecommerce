package com.api_ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api_ecommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{
    Optional<Category> findByName(String name);
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.subCategories WHERE c.parentId IS NULL AND c.canceled = false ORDER BY c.order")
    List<Category> findAllParentCategoriesWithSubCategories();
}
