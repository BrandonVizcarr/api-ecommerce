package com.api_ecommerce.services.implementation;

import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.api_ecommerce.dto.request.ProductRequestDTO;
import com.api_ecommerce.entities.Product;
import com.api_ecommerce.entities.Review;
import com.api_ecommerce.repositories.ProductRepository;
import com.api_ecommerce.repositories.ReviewRepository;
import com.api_ecommerce.services.ProductService;
import com.api_ecommerce.specifications.ProductSpecification;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    
    @Override
    public Page<Product> getProducts(String query,
            Double minPrice,
            Double maxPrice,
            Integer categoryId,
            Boolean canceled,
            int page,
            int size,
            String sortBy,
            String direction) {
        Specification<Product> spec = Specification.allOf(ProductSpecification.hasNameOrDescriptionLike(query))
                .and(ProductSpecification.priceBetween(minPrice, maxPrice))
                .and(ProductSpecification.hasCategory(categoryId))
                .and(ProductSpecification.activeEquals(canceled));
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(spec, pageable);
    }

    @Override
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + productId));
    }

    @Override
    @Transactional
    public Boolean saveProduct(ProductRequestDTO productRequestDTO) {
        Product product = modelMapper.map(productRequestDTO, Product.class);
        return productRepository.save(product).getProductId() != null;
    }

    @Override
    @Transactional
    public Boolean updateProduct(UUID productId, ProductRequestDTO productRequestDTO) {
        return productRepository.findById(productId)
                .map(existing -> {
                    modelMapper.map(productRequestDTO, existing);
                    productRepository.save(existing);
                return true;
                })
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + productId));
    }

    @Override
    public Page<Review> getReviwsByProductId(UUID prodcutId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("likes"));
        return reviewRepository.findByProductId(prodcutId,pageable);
    }

    @Override
    public Boolean deleteProductById(UUID productId) {
        return productRepository.findById(productId).map(existing ->{
            existing.setCanceled(true);
            productRepository.save(existing);
            return true;
        }).orElseThrow(() -> new EntityNotFoundException("Product not found: " + productId));
    }

}
