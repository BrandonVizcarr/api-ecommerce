package com.api_ecommerce.services;

import com.api_ecommerce.dto.request.SaleRequestDTO;
import com.api_ecommerce.entities.Sale;

public interface SaleService {

    public Sale saveSale(SaleRequestDTO saleRequestDTO);
}
