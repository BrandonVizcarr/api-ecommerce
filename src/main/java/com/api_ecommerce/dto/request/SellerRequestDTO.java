package com.api_ecommerce.dto.request;

import java.util.UUID;

import lombok.Data;

@Data
public class SellerRequestDTO {
    private Integer sellerId;
    private String name;
    private String desc;
    private Integer countryId;
    private Integer cityId;
    private String profileImg;
    private UUID userId;
}
