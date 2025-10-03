package com.api_ecommerce.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity
public class Seller {
    @Id
    @GeneratedValue
    private Integer sellerId;
    private String name;
    private String desc;
    private String email;
    private Integer countryId;
    private Integer cityId;
    private Double rate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer totalIntemsSold;
    private Boolean verified;

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
