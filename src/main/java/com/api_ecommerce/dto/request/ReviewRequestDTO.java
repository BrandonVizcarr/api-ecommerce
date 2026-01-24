package com.api_ecommerce.dto.request;

import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequestDTO {
    @NotNull(message = "desc cannot be null")
    private String desc;
    @NotNull(message = "userId cannot be null")
    private UUID userId;
    @NotNull(message = "productId cannot be null")
    private UUID productId;
    private List<String> media;
}
