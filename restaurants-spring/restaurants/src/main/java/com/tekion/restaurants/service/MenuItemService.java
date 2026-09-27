package com.tekion.restaurants.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tekion.restaurants.exception.BusinessRuleException;
import com.tekion.restaurants.exception.DuplicateResourceException;
import com.tekion.restaurants.exception.ResourceNotFoundException;
import com.tekion.restaurants.model.dto.CreateMenuItemRequest;
import com.tekion.restaurants.model.entity.MenuItem;
import com.tekion.restaurants.model.entity.Restaurant;
import com.tekion.restaurants.model.enums.Category;
import com.tekion.restaurants.model.enums.MenuItemStatus;
import com.tekion.restaurants.repository.MenuItemRepository;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantService restaurantService;

    public MenuItemService(MenuItemRepository menuItemRepository, RestaurantService restaurantService) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantService = restaurantService;
    }
    
    public MenuItem addMenuItem(Long restaurantId, CreateMenuItemRequest request) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        
        // Business Rule: Restaurant must be OPEN
        if (!restaurant.isOpen()) {
            throw new BusinessRuleException("Cannot add menu items to a closed restaurant");
        }
        
        // Business Rule: Menu item name must be unique per restaurant
        boolean exists = menuItemRepository.findByRestaurantId(restaurantId).stream()
            .anyMatch(m -> m.getName().equalsIgnoreCase(request.getName()));
        if (exists) {
            throw new DuplicateResourceException("Menu item '" + request.getName() + "' already exists in this restaurant");
        }
        
        MenuItem menuItem = new MenuItem();
        menuItem.setName(request.getName());
        menuItem.setPrice(request.getPrice());
        menuItem.setCategory(request.getCategory());
        menuItem.setStatus(MenuItemStatus.AVAILABLE);  // Default
        menuItem.setRestaurantId(restaurantId);
        
        return menuItemRepository.save(menuItem);
    }
    
    public List<MenuItem> getMenuItems(Long restaurantId, Category category, 
                                        MenuItemStatus status, String sortBy, 
                                        int page, int size) {
        // Get all items for restaurant
        List<MenuItem> items = menuItemRepository.findByRestaurantId(restaurantId);
        
        // Apply filters
        if (category != null) {
            items = items.stream().filter(m -> m.getCategory() == category).toList();
        }
        if (status != null) {
            items = items.stream().filter(m -> m.getStatus() == status).toList();
        }
        
        // Apply sorting
        if ("price,asc".equals(sortBy)) {
            items = items.stream().sorted(Comparator.comparing(MenuItem::getPrice)).toList();
        } else if ("price,desc".equals(sortBy)) {
            items = items.stream().sorted(Comparator.comparing(MenuItem::getPrice).reversed()).toList();
        }
        
        // Apply pagination
        int start = page * size;
        int end = Math.min(start + size, items.size());
        return items.subList(start, end);
    }
    
    public MenuItem updateStatus(Long menuItemId, MenuItemStatus status) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
            .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + menuItemId));
        menuItem.setStatus(status);
        return menuItemRepository.save(menuItem);
    }
    
    public void deleteMenuItem(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
            .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + menuItemId));
        menuItem.setDeleted(true);  // Soft delete
        menuItemRepository.save(menuItem);
    }
}