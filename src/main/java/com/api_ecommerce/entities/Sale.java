package com.api_ecommerce.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name = "sales")
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long saleId;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;
    @Column(name = "discount_total")
    private BigDecimal discountTotal;
    @Column(name = "final_amount", nullable = false)
    private BigDecimal finalAmount;
    @Column(name = "payment_method", nullable = false)
    private Integer paymentMethod; 
    @Column(name = "status", nullable = false)
    private Integer status; 
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> items;
}
