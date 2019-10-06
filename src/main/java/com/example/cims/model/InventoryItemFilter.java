package com.example.cims.model;

public class InventoryItemFilter {
    private CarInventoryResult car;
    private PartInventoryResult part;

    public CarInventoryResult getCar() {
        return car;
    }

    public void setCar(CarInventoryResult car) {
        this.car = car;
    }

    public PartInventoryResult getPart() {
        return part;
    }

    public void setPart(PartInventoryResult part) {
        this.part = part;
    }
}
