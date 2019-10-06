package com.example.cims.model;

import java.util.List;

public class InventoryFilter {
    private List<FavCarResult> cars;
    private List<FavPartResult> parts;

    public List<FavCarResult> getCars() {
        return cars;
    }

    public void setCars(List<FavCarResult> cars) {
        this.cars = cars;
    }

    public List<FavPartResult> getParts() {
        return parts;
    }

    public void setParts(List<FavPartResult> parts) {
        this.parts = parts;
    }
}
