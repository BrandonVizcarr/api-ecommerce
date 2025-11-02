package com.api_ecommerce.entities;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Users")
@Data
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "username")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "rate")
    private Double rate;
    @Column(name = "rol_id")
    private Integer role;
    @Column(name = "profile_img")
    private String profileImg;
}
