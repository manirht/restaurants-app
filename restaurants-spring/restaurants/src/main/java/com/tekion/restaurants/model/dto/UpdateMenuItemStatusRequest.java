package com.tekion.restaurants.model.dto;

import com.tekion.restaurants.model.enums.MenuItemStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateMenuItemStatusRequest {
    @NotNull(message = "Status is required")
    private MenuItemStatus status;
}

