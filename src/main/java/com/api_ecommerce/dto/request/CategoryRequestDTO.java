package com.api_ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDTO {

    @NotEmpty
    @NotBlank
    @Size(min = 4, message = "Name must be at least 4 characters long")
    private String name;
    private Integer order=1;
    private Integer parentId;
    private Integer categoryId;
}
