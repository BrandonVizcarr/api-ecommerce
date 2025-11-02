package com.api_ecommerce.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Entity(name = "products")
@Data
public class Product {
    
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private UUID productId;
    @Column(name = "name")
    private String name;
    @Column(name = "desc")
    private String description;
    @Column(name = "rate")
    private Double rate;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "created")
    private LocalDateTime createdAt;
    @Column(name = "updated")
    private LocalDateTime updatedAt;
    @Column(name = "media")
    private List<String> media;
    @Column(name = "canceled")
    private Boolean canceled;
    @Column(name = "cantegory_id")
    private Integer categoryId;
    @Column(name = "subcategory_id")
    private Integer subCategoryId;
    @Column(name = "seller_id")
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
