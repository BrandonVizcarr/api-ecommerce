package com.api_ecommerce.dto.request;

import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SaleRequestDTO {

    @NotNull(message = "User Id cannot be null.")
    @Pattern(
    regexp = "^[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}$",
    message = "UUID must be valid"
    )
    private Long userId;
    @NotNull(message = "Payment Method cannot be null.")
    @Min(value = 1, message = "The Payment method is invalid.")
    private Integer paymentMethod;

    @NotNull(message = "Items cannot be null.")
    @NotEmpty(message = "Items cannot be empty.")
    @Size(min = 1, message = "Must be minimum one item.")
    private List<SaleItemRequestDTO> items;
    
    @NotNull
    @DecimalMin(value = "0.00", message = "The discount cannot be negative.")
    private BigDecimal globalDiscountAmount = BigDecimal.ZERO; 
    
    @NotNull
    @DecimalMin(value = "0.00", message = "Tax cannot be negative.")
    private BigDecimal taxRate = BigDecimal.ZERO; 
    
    @DecimalMin(value = "0.00", message = "Amount paid cannot be negative")
    private BigDecimal amountPaid;
}
