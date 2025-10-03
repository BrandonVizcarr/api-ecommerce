package com.api_ecommerce.services.implementation;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.api_ecommerce.dto.request.ProductRequestDTO;
import com.api_ecommerce.entities.Product;
import com.api_ecommerce.repositories.ProductRepository;
import com.api_ecommerce.services.ProductService;
import com.api_ecommerce.specifications.ProductSpecification;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Page<Product> getProducts(String query,
            Double minPrice,
            Double maxPrice,
            Integer categoryId,
            int page,
            int size,
            String sortBy,
            String direction) {
        Specification<Product> spec = Specification.allOf(ProductSpecification.hasNameOrDescriptionLike(query))
                .and(ProductSpecification.priceBetween(minPrice, maxPrice))
                .and(ProductSpecification.hasCategory(categoryId));
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(spec,pageable);
    }

    @Override
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));
    }

    @Override
    public Boolean saveProduct(ProductRequestDTO productRequestDTO) {
        
        Product product = modelMapper.map(productRequestDTO, Product.class);
        return productRepository.save(product).getProductId() != null;
    }

    @Override
    public Boolean updateProduct(UUID productId, ProductRequestDTO productRequestDTO) {
        return productRepository.findById(productId)
                .map(existing -> {
                    Product updated = modelMapper.map(productRequestDTO, Product.class);
                    updated.setProductId(productId);
                    return productRepository.save(updated).getUpdatedAt() != existing.getUpdatedAt();
                })
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));
    }

}
