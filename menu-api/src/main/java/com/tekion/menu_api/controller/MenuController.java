package com.tekion.menu_api.controller;

import com.tekion.menu_api.model.MenuItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    // In-memory menu data (simulating a database)
    private List<MenuItem> menu = List.of(
        new MenuItem(1, "Margherita Pizza", 12.99),
        new MenuItem(2, "Pasta Carbonara", 14.99),
        new MenuItem(3, "Caesar Salad", 8.99),
        new MenuItem(4, "Tiramisu", 6.99)
    );

    @GetMapping("/api/menu")
    public List<MenuItem> getMenu() {
        return menu;
    }
}

