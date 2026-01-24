package com.api_ecommerce.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDTO {
    private UUID userId;
    private String name;
    private String lastName;
    private String profileImg;
}
