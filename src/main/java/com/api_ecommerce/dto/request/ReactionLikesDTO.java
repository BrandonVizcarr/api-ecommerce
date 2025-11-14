package com.api_ecommerce.dto.request;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReactionLikesDTO {
    @NotBlank(message = "Action is required (like or dislike)")
    @Pattern(
        regexp = "^(like|dislike)$",
        message = "Action must be either 'like' or 'dislike'"
    )
    private String action;
    @Pattern(
    regexp = "^[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}$",
    message = "UUID must be valid"
    )
    private UUID userId;
}
