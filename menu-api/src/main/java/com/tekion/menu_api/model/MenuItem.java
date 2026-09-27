package com.tekion.menu_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItem {
    private int id;
    private String name;
    private double price;
}
