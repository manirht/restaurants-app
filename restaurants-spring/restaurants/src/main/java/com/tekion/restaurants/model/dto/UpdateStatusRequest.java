package com.tekion.restaurants.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    @NotNull
    private boolean open;

    public boolean isOpen() {
        return open;
    }
}
