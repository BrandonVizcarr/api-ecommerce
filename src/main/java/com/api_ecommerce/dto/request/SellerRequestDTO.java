package com.api_ecommerce.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SellerRequestDTO {
    private Integer sellerId;
    private String name;
    private String desc;
    private Integer countryId;
    private Integer cityId;
    private String profileImg;
    @NotBlank
    @Pattern(
    regexp = "^[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}$",
    message = "UUID must be valid"
    )
    private UUID userId;
}
