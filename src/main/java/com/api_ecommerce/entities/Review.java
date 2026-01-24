package com.api_ecommerce.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;
    @Column(name = "description")
    private String desc;
    @Column(name = "likes")
    private Integer likes = 0;
    @Column(name = "dislikes")
    private Integer dislikes = 0;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "product_id")
    private UUID productId;
    @Column(name = "media")
    private List<String> media;
    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
