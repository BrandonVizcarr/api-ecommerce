package com.api_ecommerce.services;

import com.api_ecommerce.dto.request.ReactionLikesDTO;
import com.api_ecommerce.dto.request.ReviewRequestDTO;
import com.api_ecommerce.entities.Review;

public interface ReviewService {
    
    public Review saveReview(ReviewRequestDTO reviewRequestDTO);
    public Review patchReview(Long reviewId, ReactionLikesDTO reactionLikesDTO);
    public Boolean deleteReview(Long reviewId);
}
