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
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Entity(name = "products")
@Data
public class Product {
    
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private UUID productId;
    @Column(name = "product_name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "rate")
    private Double rate;
    @Column(name = "rate_count")
    private Integer rateCount;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "previous_price")
    private BigDecimal previousPrice;
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "stock", nullable = false)
    private Integer stock;
    @Column(name = "created")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updated")
    private LocalDateTime updatedAt = LocalDateTime.now();
    @Column(name = "media")
    private List<String> media;
    @Column(name = "canceled")
    private Boolean canceled = false;
    @Column(name = "cantegory_id")
    private Integer categoryId;
    @Column(name = "subcategory_id")
    private Integer subCategoryId;
    @Column(name = "seller_id", nullable = false)
    private Long sellerId;
    @Column(name = "brand_id")
    private Integer brandId;
    @Column(name = "desc_condition")
    private String descCondition;
    @Column(name = "free_delivery")
    private Boolean freeDelivery;
    @Column(name = "fast_delivery")
    private Boolean fastDelivery;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_Id", nullable = false, updatable = false, insertable = false) 
    private Seller seller;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
