package com.api_ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.api_ecommerce.dto.response.ApiResponseDTO;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(ex.getMessage(),HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO> handleAllExceptions(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDTO(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponseDTO> handlerEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDTO(ex.getMessage(),HttpStatus.NOT_FOUND));
    }
    
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ApiResponseDTO> handlerRuntimeException(EntityExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseDTO(ex.getMessage(),HttpStatus.CONFLICT));
    }
}
