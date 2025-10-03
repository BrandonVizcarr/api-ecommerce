package com.api_ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    private String name;
    private String lastName;
    private String email;
    private Double rate;
    private Integer employeeId;
}
