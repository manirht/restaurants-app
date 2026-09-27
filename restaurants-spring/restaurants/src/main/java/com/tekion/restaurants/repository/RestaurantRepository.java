package com.tekion.restaurants.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.tekion.restaurants.model.entity.Restaurant;

@Repository
public class RestaurantRepository {
    private final Map<Long, Restaurant> restaurants = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            restaurant.setId(idGenerator.getAndIncrement());
        }
        restaurants.put(restaurant.getId(), restaurant);
        return restaurant;
    }
    
    public Optional<Restaurant> findById(Long id) {
        return Optional.ofNullable(restaurants.get(id));
    }
    
    public boolean existsByName(String name) {
        return restaurants.values().stream()
            .anyMatch(r -> r.getName().equalsIgnoreCase(name));
    }
}
