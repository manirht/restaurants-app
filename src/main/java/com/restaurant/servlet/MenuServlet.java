package com.restaurant.servlet;

import com.google.gson.Gson;
import com.restaurant.model.MenuItem;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple Servlet that handles GET /api/menu
 * Returns a list of restaurant menu items as JSON
 */
@WebServlet("/api/menu")
public class MenuServlet extends HttpServlet {

    // In-memory menu data (simulating a database)
    private List<MenuItem> menu = new ArrayList<>();
    private Gson gson = new Gson();

    @Override
    public void init() {
        // Add some sample menu items
        menu.add(new MenuItem(1, "Margherita Pizza", 12.99));
        menu.add(new MenuItem(2, "Pasta Carbonara", 14.99));
        menu.add(new MenuItem(3, "Caesar Salad", 8.99));
        menu.add(new MenuItem(4, "Tiramisu", 6.99));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Step 1: Set response type to JSON
        response.setContentType("application/json");

        // Step 2: Convert menu list to JSON
        String json = gson.toJson(menu);

        // Step 3: Write JSON to response
        response.getWriter().write(json);
    }
}

