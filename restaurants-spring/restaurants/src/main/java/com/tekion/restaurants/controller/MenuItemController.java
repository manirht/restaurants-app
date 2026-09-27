package com.tekion.restaurants.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tekion.restaurants.model.dto.CreateMenuItemRequest;
import com.tekion.restaurants.model.dto.MenuItemResponse;
import com.tekion.restaurants.model.dto.UpdateMenuItemStatusRequest;
import com.tekion.restaurants.model.entity.MenuItem;
import com.tekion.restaurants.model.enums.Category;
import com.tekion.restaurants.model.enums.MenuItemStatus;
import com.tekion.restaurants.service.MenuItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping("/restaurants/{restaurantId}/menu-items")
    public ResponseEntity<MenuItemResponse> addMenuItem(
            @PathVariable Long restaurantId,
            @Valid @RequestBody CreateMenuItemRequest request) {
        MenuItem menuItem = menuItemService.addMenuItem(restaurantId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(menuItem));
    }
    
    @GetMapping("/restaurants/{restaurantId}/menu-items")
    public ResponseEntity<List<MenuItemResponse>> getMenuItems(
            @PathVariable Long restaurantId,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) MenuItemStatus status,
            @RequestParam(defaultValue = "price,asc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<MenuItem> items = menuItemService.getMenuItems(restaurantId, category, status, sort, page, size);
        return ResponseEntity.ok(items.stream().map(this::toResponse).toList());
    }
    
    @PatchMapping("/menu-items/{menuItemId}/status")
    public ResponseEntity<MenuItemResponse> updateStatus(
            @PathVariable Long menuItemId,
            @RequestBody UpdateMenuItemStatusRequest request) {
        MenuItem menuItem = menuItemService.updateStatus(menuItemId, request.getStatus());
        return ResponseEntity.ok(toResponse(menuItem));
    }
    
    @DeleteMapping("/menu-items/{menuItemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);
        return ResponseEntity.noContent().build();
    }

    private MenuItemResponse toResponse(MenuItem menuItem) {
        MenuItemResponse response = new MenuItemResponse();
        response.setId(menuItem.getId());
        response.setName(menuItem.getName());
        response.setPrice(menuItem.getPrice());
        response.setCategory(menuItem.getCategory());
        response.setStatus(menuItem.getStatus());
        return response;
    }
}