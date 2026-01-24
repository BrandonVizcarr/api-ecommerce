package com.api_ecommerce.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @Column(name = "fistname")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @JsonIgnore
    @Column(name = "email")
    private String email;
    @JsonIgnore
    @Column(name = "pass")
    private String password;
    @JsonIgnore
    @Column(name = "rol_id")
    private Integer role = 1;
    @Column(name = "profile_img")
    private String profileImg;
}
