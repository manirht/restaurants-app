package com.tekion.restaurants.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tekion.restaurants.model.entity.MenuItem;

@Repository
public class MenuItemRepository {
    private final Map<Long, MenuItem> menuItems = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public MenuItem save(MenuItem menuItem) {
        if (menuItem.getId() == null) {
            menuItem.setId(idGenerator.getAndIncrement());
        }
        menuItems.put(menuItem.getId(), menuItem);
        return menuItem;
    }
    
    public Optional<MenuItem> findById(Long id) {
        return Optional.ofNullable(menuItems.get(id));
    }
    
    public List<MenuItem> findByRestaurantId(Long restaurantId) {
        return menuItems.values().stream()
            .filter(m -> m.getRestaurantId().equals(restaurantId))
            .filter(m -> !m.isDeleted())  
            .collect(Collectors.toList());
    }
}
