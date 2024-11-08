package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @Size(min = 2, message = "Product name must contain atleast 3 characters ")
    @NotBlank(message = "Product name should not be null")
    private String productName;

    private String image;

    @Size(min = 10, message = "Product name must contain atleast 10 characters")
    @NotNull(message = "Product description should not be null")
    @NotBlank(message = "Product description should not be null")
    private String description;
    @NotNull(message = "Product quantity should not be null")
    private Integer quantity;
    @NotNull(message = "Product price should not be null")
    private double price;

    private double discount;
    private double specialPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
