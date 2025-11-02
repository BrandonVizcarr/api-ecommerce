package com.api_ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "menu")
@Data
public class Menu {

    @Id
    @Column(name = "id")
    private Integer idMenu;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String urlMenu;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "parent_id")
    private Integer parentId;
    @Column(name = "active")
    private Boolean active;
    @Column(name= "order")
    private Integer order;
}
