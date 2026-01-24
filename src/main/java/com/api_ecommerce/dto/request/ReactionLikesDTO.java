package com.api_ecommerce.dto.request;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReactionLikesDTO {
    @NotBlank(message = "Action is required (like or dislike)")
    @Pattern(regexp = "^(like|dislike)$", message = "Action must be either 'like' or 'dislike'")
    private String action;
    private UUID userId;
}
