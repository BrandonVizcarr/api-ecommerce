package com.api_ecommerce.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue
    private UUID productId;
    private String name;
    private String description;
    private Double rate;
    private BigDecimal price;
    private BigDecimal discount;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> links;
    private Boolean canceled;
    private Integer categoryId;
    private Integer subCategoryId;
    private Integer sellerId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sellerId", nullable = false, updatable = false, insertable = false) 
    private Seller seller;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
