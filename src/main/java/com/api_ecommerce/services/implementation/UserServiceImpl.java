package com.api_ecommerce.services.implementation;

import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.api_ecommerce.dto.request.UserRequestDTO;
import com.api_ecommerce.dto.response.UserResponseDTO;
import com.api_ecommerce.entities.User;
import com.api_ecommerce.repositories.UserRepository;
import com.api_ecommerce.services.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new EntityExistsException("User with this email already exists");
        }

        User user = modelMapper.map(userRequestDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user = userRepository.save(user);

        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: "+userId));
        return modelMapper.map(user, UserResponseDTO.class);
    }

}
