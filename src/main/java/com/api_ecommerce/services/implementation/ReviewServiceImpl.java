package com.api_ecommerce.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.api_ecommerce.dto.request.ReactionLikesDTO;
import com.api_ecommerce.dto.request.ReviewRequestDTO;
import com.api_ecommerce.entities.Review;
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

    @Override
    @Transactional
    public Review saveReview(ReviewRequestDTO reviewRequestDTO) {
        Review review = modelMapper.map(reviewRequestDTO, Review.class); 
        return reviewRepository.save(review);   
    }

    @Override
    @Transactional
    public Review patchReview(Long reviewId, ReactionLikesDTO reactionLikesDTO) {
        return reviewRepository.findById(reviewId).map(existing ->{
            switch (reactionLikesDTO.getAction()) {
                case "like" -> existing.setLikes(existing.getLikes() + 1);
                case "dislike" -> existing.setDislikes(existing.getDislikes() + 1);
                default -> throw new IllegalArgumentException("Invalid action: " + reactionLikesDTO.getAction());
            }

            return reviewRepository.save(existing);
        }).orElseThrow(()-> new EntityNotFoundException("review not found: "+reviewId));
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
