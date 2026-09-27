package com.tekion.restaurants.model.entity;

import com.tekion.restaurants.model.enums.Category;
import com.tekion.restaurants.model.enums.MenuItemStatus;

import lombok.Data;

@Data
public class MenuItem {
    private Long id;
    private String name;
    private double price;
    private Category category;
    private MenuItemStatus status;
    private Long restaurantId;
    private boolean deleted;
}
