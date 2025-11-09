package com.api_ecommerce.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequestDTO {
    private UUID productId;
    @Size(min = 4, message = "Name must be at least 4 characters long")
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    @NotNull(message = "Price is required")
    private BigDecimal price;
    private BigDecimal discount;
    @NotNull(message = "Stock is required")
    private Integer stock;
    private List<String> links;
    @NotNull(message = "Category is required")
    private Integer categoryId;
    private Integer subCategoryId;
    @NotNull(message = "Seller is required")
    private Integer sellerId;
}
