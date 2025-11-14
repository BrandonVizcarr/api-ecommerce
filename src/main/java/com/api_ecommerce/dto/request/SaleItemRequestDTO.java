package com.api_ecommerce.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SaleItemRequestDTO {
    @NotNull(message = "Product id cannot be null.")
    @Pattern(
    regexp = "^[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}$",
    message = "UUID must be valid"
    )
    private UUID productId;
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1,message = "Quantity must be minimum one")
    private Integer quantity;
    @NotNull(message = "PriceUnit cannot b null")
    private BigDecimal priceUnit;
    private BigDecimal discount;
}
