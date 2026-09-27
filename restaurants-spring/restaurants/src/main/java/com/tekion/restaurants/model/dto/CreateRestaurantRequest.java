package com.tekion.restaurants.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class CreateRestaurantRequest {
    @NotBlank(message = "Restaurant name cannot be blank")
    private String name;
    private String location;
}
