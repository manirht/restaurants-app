package com.tekion.restaurants.model.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Restaurant {
    private Long id;
    private String name;
    private String location;
    private boolean isOpen;
    private List<MenuItem> menuItems = new ArrayList<>();
}
