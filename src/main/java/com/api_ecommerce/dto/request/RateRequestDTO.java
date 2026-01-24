package com.api_ecommerce.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RateRequestDTO {
    @NotNull(message = "Rate cannot be null")
    @Min(value = 0, message = "Minimum rate value is 0.0")
    @Max(value = 5, message = "Maximum rate value is 5.0")
    private Double rate;
    private UUID userId;
}
