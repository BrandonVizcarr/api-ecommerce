package com.api_ecommerce.controllers;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api_ecommerce.dto.request.UserRequestDTO;
import com.api_ecommerce.dto.response.ApiResponseDTO;
import com.api_ecommerce.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO(userService.saveUser(userRequestDTO),HttpStatus.CREATED));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO> getUserById(@PathVariable("userId") UUID userId){
        return ResponseEntity.ok(new ApiResponseDTO(userService.getUserById(userId),HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO> getUsers(@RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size){
        return ResponseEntity.ok(new ApiResponseDTO(userService.getUsers(page, size),HttpStatus.OK));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO> deleteUser(@PathVariable("userId") UUID userId){
        return ResponseEntity.ok(new ApiResponseDTO(userService.deleteUser(userId),HttpStatus.OK));
    }

}
