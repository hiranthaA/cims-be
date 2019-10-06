package com.example.cims.model;

import java.util.List;

public class InventoryListFilter {
    private List<CarInventoryResult> cars;
    private List<PartInventoryResult> parts;

    public List<CarInventoryResult> getCars() {
        return cars;
    }

    public void setCars(List<CarInventoryResult> cars) {
        this.cars = cars;
    }

    public List<PartInventoryResult> getParts() {
        return parts;
    }

    public void setParts(List<PartInventoryResult> parts) {
        this.parts = parts;
    }
}
