package com.example.cims.model;

import java.util.Date;

public class CarInventoryResultExt extends CarInventoryResult {

    private int quantity;

    public CarInventoryResultExt(int invid, Date addedon, Date expon, String itemtype, int stock, String state, int car_id, String plateno, String brand, String model, int prodyr, String color, int mileage, String photo, String description, int price, int downpayment,int quantity) {
        super(invid, addedon, expon, itemtype, stock, state, car_id, plateno, brand, model, prodyr, color, mileage, photo, description, price, downpayment);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
