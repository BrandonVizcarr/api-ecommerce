package com.api_ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO{
    private Meta meta;
    private Object data;

    public ApiResponseDTO(Object data, HttpStatus status) {
        this.meta = new Meta(status.value(),status.getReasonPhrase());
        this.data = data;
    }

    public ApiResponseDTO(HttpStatus status) {
        this.meta = new Meta(status.value(),status.getReasonPhrase());
        this.data = null;
    }

    @Data
    public static class Meta {
        private String transactionId;
        private int statusCode;
        private String message;
        private LocalDateTime timestamp;

        public Meta(int statusCode, String message) {
            this.transactionId = UUID.randomUUID().toString();
            this.statusCode = statusCode;
            this.message = message;
            this.timestamp = LocalDateTime.now();
        }
    }

}