package com.api_ecommerce.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProductRequestDTO {
    private UUID productId;
    @NotEmpty
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    @NotEmpty
    private Integer stock;
    private List<String> links;
    @NotEmpty
    private Integer categoryId;
    private Integer subCategoryId;
    @NotEmpty
    private Integer sellerId;
}
