package com.api_ecommerce.services.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.api_ecommerce.dto.request.SaleItemRequestDTO;
import com.api_ecommerce.dto.request.SaleRequestDTO;
import com.api_ecommerce.entities.Product;
import com.api_ecommerce.entities.Sale;
import com.api_ecommerce.entities.SaleItem;
import com.api_ecommerce.repositories.ProductRepository;
import com.api_ecommerce.repositories.SaleRepository;
import com.api_ecommerce.services.InventoryService;
import com.api_ecommerce.services.SaleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final InventoryService inventoryService;

    @Override
    @Transactional
    public Sale saveSale(SaleRequestDTO saleRequestDTO) {
        final Long customerUserId = saleRequestDTO.getUserId();
        Sale sale = modelMapper.map(saleRequestDTO, Sale.class);
        sale.setStatus(1);

        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal discountTotal = BigDecimal.ZERO;
        BigDecimal finalAmount = BigDecimal.ZERO;

        List<SaleItem> calculatedSaleItems = new ArrayList<>();
        for (SaleItemRequestDTO itemDTO : saleRequestDTO.getItems()) {

            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Product not found: " + itemDTO.getProductId()));

            if (product.getSellerId().equals(customerUserId)) {
                throw new IllegalStateException("User (ID: " + customerUserId
                        + ") cannot buy their own product (ID: " + product.getProductId() + ").");
            }

            SaleItem saleItem = modelMapper.map(itemDTO, SaleItem.class);

            BigDecimal actualPriceUnit = product.getPrice();
            BigDecimal subtotalBeforeDiscount = actualPriceUnit.multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            BigDecimal itemDiscount = itemDTO.getDiscount() != null ? itemDTO.getDiscount() : BigDecimal.ZERO;
            BigDecimal subtotalAfterDiscount = subtotalBeforeDiscount.subtract(itemDiscount);

            saleItem.setPriceUnit(actualPriceUnit);
            saleItem.setSubtotal(subtotalAfterDiscount);

            totalAmount = totalAmount.add(subtotalBeforeDiscount);
            discountTotal = discountTotal.add(itemDiscount);
            finalAmount = finalAmount.add(subtotalAfterDiscount);

            saleItem.setSale(sale);
            calculatedSaleItems.add(saleItem);

            inventoryService.reduceStock(
                itemDTO.getProductId(), 
                itemDTO.getQuantity()
            );
        }

        finalAmount = finalAmount.subtract(saleRequestDTO.getGlobalDiscountAmount());
        discountTotal = discountTotal.add(saleRequestDTO.getGlobalDiscountAmount());

        sale.setTotalAmount(totalAmount);
        sale.setDiscountTotal(discountTotal);
        sale.setFinalAmount(finalAmount);
        sale.setItems(calculatedSaleItems);

        return saleRepository.save(sale);

    }

}
