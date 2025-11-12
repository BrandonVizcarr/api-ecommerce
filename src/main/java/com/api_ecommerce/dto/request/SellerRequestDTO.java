package com.api_ecommerce.dto.request;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SellerRequestDTO {
    private Integer sellerId;
    @Size(min = 4, message = "Name must be at least 4 characters long")
    @NotBlank(message = "Name is required")
    private String name;
    private String desc;
    private Integer countryId;
    private Double rate;
    private Integer cityId;
    private String profileImg;
    @NotBlank
    @Pattern(
    regexp = "^[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}$",
    message = "UUID must be valid"
    )
    private UUID userId;
}
