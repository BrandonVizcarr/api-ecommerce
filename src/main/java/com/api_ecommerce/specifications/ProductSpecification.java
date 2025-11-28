package com.api_ecommerce.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.api_ecommerce.entities.Product;

import jakarta.persistence.criteria.Predicate;

public class ProductSpecification {
     public static Specification<Product> hasNameOrDescriptionLike(String term) {
        return (root, query, cb) -> {
            if (term == null || term.isEmpty()) return cb.conjunction();

            String likePattern = "%" + term.toLowerCase() + "%";
            Predicate namePredicate = cb.like(cb.lower(root.get("name")), likePattern);
            Predicate descPredicate = cb.like(cb.lower(root.get("description")), likePattern);
            return cb.or(namePredicate, descPredicate);
        };
    }

    public static Specification<Product> priceBetween(Double minPrice, Double maxPrice) {
        return (root, query, cb) -> {
            if (minPrice == null && maxPrice == null) return cb.conjunction();
            if (minPrice != null && maxPrice != null) return cb.between(root.get("price"), minPrice, maxPrice);
            if (minPrice != null) return cb.greaterThanOrEqualTo(root.get("price"), minPrice);
            return cb.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    public static Specification<Product> hasCategory(Integer categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) return cb.conjunction();
            return cb.equal(root.get("categoryId"), categoryId);
        };
    }

    public static Specification<Product> hasSubCategory(Integer subCategoryId){
        return (root, query, cb)->{
            if(subCategoryId == null) return cb.conjunction();
            return cb.equal(root.get("subCategoryId"), subCategoryId);
        };
    }

    public static Specification<Product> activeEquals(Boolean canceled) {
        return (root, query, cb) -> {
            return cb.equal(root.get("canceled"), canceled);
        };
    }
}
