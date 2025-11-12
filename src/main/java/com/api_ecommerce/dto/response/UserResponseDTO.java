package com.api_ecommerce.dto.response;

import lombok.Data;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class UserResponseDTO {
    private UUID userId;
    private String name;
    private String lastName;
    private String email;
    private Double rate;
    private Integer rateCont;
    private Integer role;
    private String profileImg;

    @JsonProperty("fullName")
    public String getFullName(){
        return this.name + ' ' + this.lastName;
    }
}