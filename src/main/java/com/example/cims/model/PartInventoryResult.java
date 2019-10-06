package com.example.cims.model;

import java.util.Date;

public class PartInventoryResult {
    private int invid;
    private Date addedon;
    private Date expon;
    private String itemtype;
    private int stock;
    private int partid;
    private String partname;
    private String brand;
    private String photo;
    private String description;
    private int price;

    public PartInventoryResult(int invid, Date addedon, Date expon, String itemtype, int stock, int partid, String partname, String brand, String photo, String description, int price) {
        this.invid = invid;
        this.addedon = addedon;
        this.expon = expon;
        this.itemtype = itemtype;
        this.stock = stock;
        this.partid = partid;
        this.partname = partname;
        this.brand = brand;
        this.photo = photo;
        this.description = description;
        this.price = price;
    }

    public int getInvid() {
        return invid;
    }

    public void setInvid(int invid) {
        this.invid = invid;
    }

    public Date getAddedon() {
        return addedon;
    }

    public void setAddedon(Date addedon) {
        this.addedon = addedon;
    }

    public Date getExpon() {
        return expon;
    }

    public void setExpon(Date expon) {
        this.expon = expon;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

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
}
