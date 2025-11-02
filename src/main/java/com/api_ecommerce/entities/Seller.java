package com.api_ecommerce.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity(name = "sellers")
public class Seller {
    @Id
    @GeneratedValue
    @Column(name = "seller_id")
    private Integer sellerId;
    @Column(name = "name")
    private String name;
    @Column(name = "desc")
    private String desc;
    @Column(name = "country_id")
    private Integer countryId;
    @Column(name = "city_id")
    private Integer cityId;
    @Column(name = "rate")
    private Double rate;
    @Column(name = "created")
    private LocalDateTime createdAt;
    @Column(name = "updated")
    private LocalDateTime updatedAt;
    @Column(name = "sold_items")
    private Integer soldItems;
    @Column(name = "verified")
    private Boolean verified;
    @Column(name = "profile_img")
    private String profileImg;
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
