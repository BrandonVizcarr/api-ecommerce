package com.api_ecommerce.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue
    private UUID userId;
    private String name;
    private String lastName;
    private String email;
    private Double rate;
    private Integer employeeId;
}
