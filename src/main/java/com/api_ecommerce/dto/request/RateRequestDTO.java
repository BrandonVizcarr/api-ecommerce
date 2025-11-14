package com.api_ecommerce.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RateRequestDTO {
    @NotNull(message = "Rate cannot be null")
    @Min(value = 0, message = "Minimum rate value is 0.0")
    @Max(value = 5, message = "Maximum rate value is 5.0")
    private Double rate;
    @Pattern(
    regexp = "^[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}$",
    message = "UUID must be valid"
    )
    private UUID userId;
}
