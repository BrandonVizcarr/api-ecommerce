package com.api_ecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_ecommerce.entities.User;

public interface UserRepository extends JpaRepository<User,UUID>{
    Optional<User> findByEmail(String email);
}
