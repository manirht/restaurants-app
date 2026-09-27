package com.tekion.restaurants.model.dto;

import com.tekion.restaurants.model.enums.Category;
import com.tekion.restaurants.model.enums.MenuItemStatus;

import lombok.Data;
@Data
public class MenuItemResponse {
    private Long id;
    private String name;
    private double price;
    private Category category;
    private MenuItemStatus status;
}