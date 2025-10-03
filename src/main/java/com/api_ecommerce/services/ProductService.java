package com.api_ecommerce.services;

import java.util.UUID;
import org.springframework.data.domain.Page;

import com.api_ecommerce.dto.request.ProductRequestDTO;
import com.api_ecommerce.entities.Product;

public interface ProductService {
    public Page<Product> getProducts(String query,
            Double minPrice,
            Double maxPrice,
            Integer categoryId,
            int page,
            int size,
            String sortBy,
            String direction);
    public Product getProductById(UUID productId);
    public Boolean saveProduct(ProductRequestDTO productRequestDTO);
    public Boolean updateProduct(UUID productId,ProductRequestDTO productRequestDTO);
}
