package com.example.cims.model;

import java.util.Date;

public class FavPartResult {
    private int inv_id;
    private Date added_on;
    private Date exp_on;
    private String item_type;
    private int stock;
    private int part_id;
    private String part_name;
    private String brand;
    private String photo;
    private String description;
    private int price;

    public FavPartResult(int inv_id, Date added_on, Date exp_on, String item_type, int stock, int part_id, String part_name, String brand, String photo, String description, int price) {
        this.inv_id = inv_id;
        this.added_on = added_on;
        this.exp_on = exp_on;
        this.item_type = item_type;
        this.stock = stock;
        this.part_id = part_id;
        this.part_name = part_name;
        this.brand = brand;
        this.photo = photo;
        this.description = description;
        this.price = price;
    }

    public int getInv_id() {
        return inv_id;
    }

    public void setInv_id(int inv_id) {
        this.inv_id = inv_id;
    }

    public Date getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Date added_on) {
        this.added_on = added_on;
    }

    public Date getExp_on() {
        return exp_on;
    }

    public void setExp_on(Date exp_on) {
        this.exp_on = exp_on;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPart_id() {
        return part_id;
    }

    public void setPart_id(int part_id) {
        this.part_id = part_id;
    }

    public String getPart_name() {
        return part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
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
}
