package com.example.cims.model;

import java.util.Date;

public class PartInventoryResultExt extends PartInventoryResult {

    private int quantity;

    public PartInventoryResultExt(int invid, Date addedon, Date expon, String itemtype, int stock, String state, int partid, String partname, String brand, String photo, String description, int price, int quantity) {
        super(invid, addedon, expon, itemtype, stock, state, partid, partname, brand, photo, description, price);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
