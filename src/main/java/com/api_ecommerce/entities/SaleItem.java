package com.api_ecommerce.entities;

import java.math.BigDecimal;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "sale_items")
@Data
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_item_id")
    private Long saleItemId;
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;
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
