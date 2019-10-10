package com.example.cims.model;

import com.example.cims.Entity.Car;
import com.example.cims.Entity.Part;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private List<OrderItemCar> cars;
    private List<OrderItemPart> parts;

    public Items() {
        this.cars = new ArrayList<>();
        this.parts = new ArrayList<>();
    }

    public List<OrderItemCar> getCars() {
        return cars;
    }

    public void setCars(List<OrderItemCar> cars) {
        this.cars = cars;
    }

    public List<OrderItemPart> getParts() {
        return parts;
    }

    public void setParts(List<OrderItemPart> parts) {
        this.parts = parts;
    }
}