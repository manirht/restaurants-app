package com.tekion.restaurants.model.dto;

import com.tekion.restaurants.model.enums.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
@Data
public class CreateMenuItemRequest {
     @NotBlank(message = "Name is required")
    private String name;
    
    @Positive(message = "Price must be greater than 0")
    private double price;
    
    @NotNull(message = "Category is required")
    private Category category;
}
