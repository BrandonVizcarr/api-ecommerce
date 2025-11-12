package com.api_ecommerce.entities;

import java.math.BigDecimal;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "sale_items")
@Data
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_item_id")
    private Long saleItemId;
    @Column(name = "sale_id")
    private Long sale;
    @Column(name = "product_id")
    private UUID productId;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price_unit")
    private BigDecimal priceUnit;
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "subtotal")
    private BigDecimal subtotal;
}
