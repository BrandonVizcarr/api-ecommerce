package com.api_ecommerce.dto.response;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDTO(
    UUID userId,
    String name,
    String lastName,
    String email,
    Double rate,
    Integer role,
    String profileImg
) {
    @JsonProperty("fullName")
    public String fullName(){
        return name + ' ' + lastName;
    }
}