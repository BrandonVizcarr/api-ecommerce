package com.api_ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "roles")
@Data
public class Role {

    @Id
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "description")
    private String desc;
}
