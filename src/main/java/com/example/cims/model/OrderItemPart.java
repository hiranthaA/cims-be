package com.example.cims.model;

import com.example.cims.Entity.Part;

public class OrderItemPart{
    private int partid;
    private String partname;
    private String brand;
    private String photo;
    private String description;
    private int price;
    private int invid;
    private int bought_price;
    private int bought_quantity;

    public int getPartid() {
        return partid;
    }

    public void setPartid(int partid) {
        this.partid = partid;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInvid() {
        return invid;
    }

    public void setInvid(int invid) {
        this.invid = invid;
    }

    public int getBought_price() {
        return bought_price;
    }

    public void setBought_price(int bought_price) {
        this.bought_price = bought_price;
    }

    public int getBought_quantity() {
        return bought_quantity;
    }

    public void setBought_quantity(int bought_quantity) {
        this.bought_quantity = bought_quantity;
    }
}