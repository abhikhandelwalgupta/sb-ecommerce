package com.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name = "categories")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Size(min = 2, max = 15, message = "Size should be in range of 2 to 15")
    @NotNull(message = "Category cannot be empty")
    @NotBlank(message = "Category cannot be empty")
    private String categoryName;


}
