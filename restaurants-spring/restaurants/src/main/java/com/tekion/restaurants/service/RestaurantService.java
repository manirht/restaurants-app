package com.tekion.restaurants.service;

import org.springframework.stereotype.Service;

import com.tekion.restaurants.exception.DuplicateResourceException;
import com.tekion.restaurants.exception.ResourceNotFoundException;
import com.tekion.restaurants.model.dto.CreateRestaurantRequest;
import com.tekion.restaurants.model.entity.Restaurant;
import com.tekion.restaurants.repository.RestaurantRepository;



@Service
public class RestaurantService {
    
    private final RestaurantRepository restaurantRepository;
    
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    
    public Restaurant createRestaurant(CreateRestaurantRequest request) {
        // Business Rule: Name must be unique
        if (restaurantRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Restaurant with name '" + request.getName() + "' already exists");
        }
        
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setLocation(request.getLocation());
        restaurant.setOpen(true);  // Default: OPEN
        
        return restaurantRepository.save(restaurant);
    }
    
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
    }
    
    public Restaurant updateStatus(Long id, boolean isOpen) {
        Restaurant restaurant = getRestaurantById(id);
        restaurant.setOpen(isOpen);
        return restaurantRepository.save(restaurant);
    }
}