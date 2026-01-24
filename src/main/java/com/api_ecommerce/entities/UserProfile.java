package com.api_ecommerce.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Users")
@Data
public class UserProfile {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "fistname")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "profile_img")
    private String profileImg;
}
