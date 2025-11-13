package com.api_ecommerce.dto.request;

import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReviewRequestDTO {
    @NotNull(message = "desc cannot be null")
    private String desc;
    @NotNull(message = "userId cannot be null")
    @Pattern(
    regexp = "^[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}$",
    message = "UUID must be valid"
    )
    private UUID userId;
    @NotNull(message = "productId cannot be null")
    @Pattern(
    regexp = "^[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}$",
    message = "UUID must be valid"
    )
    private UUID productId;
    private List<String> media;
}
