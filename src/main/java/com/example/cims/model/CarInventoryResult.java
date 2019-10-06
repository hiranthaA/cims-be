package com.example.cims.model;

import javax.persistence.*;
import java.util.Date;
//
//@SqlResultSetMapping(
//        name="FavCarResult",
//        classes={
//                @ConstructorResult(
//                        targetClass= CarInventoryResult.class,
//                        columns={
//                                @ColumnResult(name="inv_id"),
//                                @ColumnResult(name="added_on", type=Date.class),
//                                @ColumnResult(name="exp_on", type=Date.class),
//                                @ColumnResult(name="item_type"),
//                                @ColumnResult(name="stock"),
//                                @ColumnResult(name="plate_no"),
//                                @ColumnResult(name="brand"),
//                                @ColumnResult(name="model"),
//                                @ColumnResult(name="prod_yr"),
//                                @ColumnResult(name="color"),
//                                @ColumnResult(name="mileage"),
//                                @ColumnResult(name="photo"),
//                                @ColumnResult(name="description"),
//                                @ColumnResult(name="price"),
//                                @ColumnResult(name="down_payment")
//                        })
//        })
//@NamedQueries(
//        {
//                @NamedQuery(
//                        name="FavCarFilter.findFavoritesByUserID",
//                        query="select i.inv_id, i.added_on as added_on, i.exp_on as exp_on, i.item_type as item_type, i.stock as stock, c.car_id as car_id, c.plate_no as plate_no, c.brand as brand, c.model as model, c.prod_yr as prod_yr, c.color as color, c.mileage as mileage, c.photo as photo, c.description as description, c.price as price, c.down_payment as down_payment from Inventory i, Favourite f, Car c where f.inv_id=i.inv_id and i.item_type=:filter and i.inv_id=c.inv_id and f.user_id=:userid"
//                ),
//        }
//)

@Entity
public class CarInventoryResult {

    @Id
    private int invid;
    private Date addedon;
    private Date expon;
    private String itemtype;
    private int stock;
    private int car_id;
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

    public CarInventoryResult(int invid, Date addedon, Date expon, String itemtype, int stock, int car_id, String plateno, String brand, String model, int prodyr, String color, int mileage, String photo, String description, int price, int downpayment) {
        this.invid = invid;
        this.addedon = addedon;
        this.expon = expon;
        this.itemtype = itemtype;
        this.stock = stock;
        this.car_id = car_id;
        this.plateno = plateno;
        this.brand = brand;
        this.model = model;
        this.prodyr = prodyr;
        this.color = color;
        this.mileage = mileage;
        this.photo = photo;
        this.description = description;
        this.price = price;
        this.downpayment = downpayment;
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

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
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
}
