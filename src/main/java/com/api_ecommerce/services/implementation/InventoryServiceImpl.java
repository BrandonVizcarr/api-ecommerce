package com.api_ecommerce.services.implementation;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.api_ecommerce.entities.Product;
import com.api_ecommerce.repositories.ProductRepository;
import com.api_ecommerce.services.InventoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final ProductRepository productRepository;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void reduceStock(UUID productId, Integer quantity) {
        Product product = productRepository.findByProductId(productId)
            .orElseThrow(() -> new EntityNotFoundException("Product not found: "+productId));
        int newQuantity = product.getStock() - quantity;
        if (newQuantity < 0) {
            throw new RuntimeException("Stock is not enough for productId: "+product);
        }
        product.setStock(newQuantity);

        productRepository.save(product);
    }

   
    
}
