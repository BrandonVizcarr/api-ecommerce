package com.api_ecommerce.services.implementation;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.api_ecommerce.dto.request.ReactionLikesDTO;
import com.api_ecommerce.dto.request.ReviewRequestDTO;
import com.api_ecommerce.entities.Review;
import com.api_ecommerce.entities.ReviewReaction;
import com.api_ecommerce.repositories.ReactionRepository;
import com.api_ecommerce.repositories.ReviewRepository;
import com.api_ecommerce.services.ReviewService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final ReactionRepository reactionRepository;

    @Override
    @Transactional
    public Review saveReview(ReviewRequestDTO reviewRequestDTO) {
        Review review = modelMapper.map(reviewRequestDTO, Review.class); 
        return reviewRepository.save(review);   
    }

    @Override
    @Transactional
    public Review patchReview(Long reviewId, ReactionLikesDTO request) {
        UUID userId = request.getUserId();
        String action = request.getAction();

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found: " + reviewId));

        ReviewReaction existingReaction = reactionRepository
            .findByUserIdAndReviewId(userId, reviewId)
            .orElse(null);

        if (existingReaction == null) {
            ReviewReaction reaction = new ReviewReaction();
            reaction.setUserId(userId);
            reaction.setReviewId(reviewId);
            reaction.setReactionType(action);
            reactionRepository.save(reaction);

            if (action.equals("like")) review.setLikes(review.getLikes() + 1);
            else review.setDislikes(review.getDislikes() + 1);

        } else if (!existingReaction.getReactionType().equals(action)) {
            // Cambio de reacción
            if (existingReaction.getReactionType().equals("like")) {
                review.setLikes(review.getLikes() - 1);
                review.setDislikes(review.getDislikes() + 1);
            } else {
                review.setDislikes(review.getDislikes() - 1);
                review.setLikes(review.getLikes() + 1);
            }

            existingReaction.setReactionType(action);
            reactionRepository.save(existingReaction);

        } else {
            // Mismo like/dislike → no hacer nada o lanzar excepción
            throw new IllegalStateException("User has already reacted with the same action");
        }

        return reviewRepository.save(review);
    }


    @Override
    public Boolean deleteReview(Long reviewId) {
        return reviewRepository.findById(reviewId)
            .map(existing -> {
                reviewRepository.delete(existing);
                return true;
            })
            .orElseThrow(()-> new EntityNotFoundException("review not found: "+reviewId));
    }

}
