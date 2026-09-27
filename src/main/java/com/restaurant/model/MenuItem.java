package com.restaurant.model;

/**
 * Simple POJO representing a menu item.
 */
public class MenuItem {

    private int id;
    private String name;
    private double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters (needed for JSON serialization)
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}

