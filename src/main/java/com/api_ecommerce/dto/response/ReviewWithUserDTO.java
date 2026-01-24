package com.api_ecommerce.dto.response;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class ReviewWithUserDTO {
    private Long reviewId;
    private String desc;
    private Integer likes;
    private Integer dislikes;
    private UUID productId;
    private List<String> media;
    private UserSummaryDTO user;
}
