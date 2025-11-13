package com.api_ecommerce.services;

import java.util.UUID;
import org.springframework.data.domain.Page;

import com.api_ecommerce.dto.request.ProductRequestDTO;
import com.api_ecommerce.entities.Product;
import com.api_ecommerce.entities.Review;

public interface ProductService {
    public Page<Product> getProducts(String query,
            Double minPrice,
            Double maxPrice,
            Integer categoryId,
            Boolean canceled,
            int page,
            int size,
            String sortBy,
            String direction);
    public Product getProductById(UUID productId);
    public Boolean saveProduct(ProductRequestDTO productRequestDTO);
    public Boolean updateProduct(UUID productId,ProductRequestDTO productRequestDTO);
    public Page<Review> getReviwsByProductId(UUID prodcutId, int page, int size);
    public Boolean deleteProductById(UUID productId);
}
