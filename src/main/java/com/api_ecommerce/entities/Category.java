package com.api_ecommerce.entities;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name = "categories")
@Data
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "category_name")
    private String name;
    @Column(name = "order_cat")
    private Integer order;
    @Column(name = "canceled")
    private Boolean canceled = false;
    @Column(name = "parent_id")
    private Integer parentId;
    @OneToMany
    @JoinColumn(name = "parent_id")
    private List<Category> subCategories;
}