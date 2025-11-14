package com.api_ecommerce.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity(name = "review_reactions")
@Table(
    name = "review_reactions",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "review_id"})
)
@Data
public class ReviewReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reaction_id")
    private Long reactionId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "reaction_type")
    private String reactionType;
}
