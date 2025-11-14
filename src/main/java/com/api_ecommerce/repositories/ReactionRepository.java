package com.api_ecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_ecommerce.entities.ReviewReaction;

public interface ReactionRepository extends JpaRepository<ReviewReaction,Long>{
    Optional<ReviewReaction> findByUserIdAndReviewId(UUID userId, Long reviewId);
}
