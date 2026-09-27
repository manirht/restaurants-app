package com.tekion.restaurants.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tekion.restaurants.model.dto.CreateRestaurantRequest;
import com.tekion.restaurants.model.dto.RestaurantResponse;
import com.tekion.restaurants.model.dto.UpdateStatusRequest;
import com.tekion.restaurants.model.entity.Restaurant;
import com.tekion.restaurants.service.RestaurantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(
            @Valid @RequestBody CreateRestaurantRequest request) {
        Restaurant restaurant = restaurantService.createRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(restaurant));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(toResponse(restaurant));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RestaurantResponse> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusRequest request) {
        Restaurant restaurant = restaurantService.updateStatus(id, request.isOpen());
        return ResponseEntity.ok(toResponse(restaurant));
    }

    private RestaurantResponse toResponse(Restaurant restaurant) {
        RestaurantResponse response = new RestaurantResponse();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setLocation(restaurant.getLocation());
        response.setOpen(restaurant.isOpen());
        return response;
    }
}