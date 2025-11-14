package com.api_ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api_ecommerce.dto.request.ReactionLikesDTO;
import com.api_ecommerce.dto.request.ReviewRequestDTO;
import com.api_ecommerce.dto.response.ApiResponseDTO;
import com.api_ecommerce.services.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO> saveReview(@Valid @RequestBody ReviewRequestDTO reviewRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO(reviewService.saveReview(reviewRequestDTO),HttpStatus.CREATED));
    }

    @PatchMapping("{reviewId}")
    public ResponseEntity<ApiResponseDTO> patchReview(@PathVariable("reviewId") Long reviewId, @RequestBody ReactionLikesDTO reactionLikesDTO){
        return ResponseEntity.ok(new ApiResponseDTO(reviewService.patchReview(reviewId, reactionLikesDTO),HttpStatus.OK));
    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<ApiResponseDTO> deleteReview(@PathVariable("reviewId") Long reviewId){
        return ResponseEntity.ok(new ApiResponseDTO(reviewService.deleteReview(reviewId),HttpStatus.OK));
    }

}
