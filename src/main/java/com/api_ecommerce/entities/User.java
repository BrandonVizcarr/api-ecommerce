package com.api_ecommerce.entities;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Users")

@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "fistname")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "pass")
    private String password;
    @Column(name = "rate")
    private Double rate;
    @Column(name = "rol_id")
    private Integer role=1;
    @Column(name = "profile_img")
    private String profileImg;
}
