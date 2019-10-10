package com.example.cims.model;

import com.example.cims.Entity.Car;

public class OrderItemCar {
    private int carid;
    private String plateno;
    private String brand;
    private String model;
    private int prodyr;
    private String color;
    private int mileage;
    private String photo;
    private String description;
    private int price;
    private int downpayment;
    private int invid;
    private int bought_price;
    private int bought_quantity;

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public String getPlateno() {
        return plateno;
    }

    public void setPlateno(String plateno) {
        this.plateno = plateno;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProdyr() {
        return prodyr;
    }

    public void setProdyr(int prodyr) {
        this.prodyr = prodyr;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
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

    public int getDownpayment() {
        return downpayment;
    }

    public void setDownpayment(int downpayment) {
        this.downpayment = downpayment;
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