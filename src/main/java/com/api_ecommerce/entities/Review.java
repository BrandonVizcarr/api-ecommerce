package com.api_ecommerce.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue
    private Integer reviewId;
    private String desc;
    private Integer likes=0;
    private Integer dislikes=0;
    private UUID userId;
    
}
