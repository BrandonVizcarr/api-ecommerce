package com.api_ecommerce.services;

import java.util.UUID;

public interface InventoryService {
    
    public void reduceStock(UUID productId ,Integer quantity);
}
