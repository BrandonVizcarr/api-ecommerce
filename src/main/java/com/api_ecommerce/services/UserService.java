package com.api_ecommerce.services;

import java.util.UUID;
import com.api_ecommerce.dto.request.UserRequestDTO;
import com.api_ecommerce.dto.response.UserResponseDTO;

public interface UserService {
    
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO);
    public UserResponseDTO getUserById(UUID userId);
}
