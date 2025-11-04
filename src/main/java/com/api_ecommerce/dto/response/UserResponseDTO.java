package com.api_ecommerce.dto.response;

import java.util.UUID;
import lombok.Data;

@Data
public class UserResponseDTO {
    private UUID userId;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Double rate;
    private Integer role;
    private String profileImg;
}
