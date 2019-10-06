package com.example.cims.model;

import javax.persistence.*;
import java.util.Date;

@SqlResultSetMapping(
        name="FavCarResult",
        classes={
                @ConstructorResult(
                        targetClass= FavCarResult.class,
                        columns={
                                @ColumnResult(name="inv_id"),
                                @ColumnResult(name="added_on", type=Date.class),
                                @ColumnResult(name="exp_on", type=Date.class),
                                @ColumnResult(name="item_type"),
                                @ColumnResult(name="stock"),
                                @ColumnResult(name="plate_no"),
                                @ColumnResult(name="brand"),
                                @ColumnResult(name="model"),
                                @ColumnResult(name="prod_yr"),
                                @ColumnResult(name="color"),
                                @ColumnResult(name="mileage"),
                                @ColumnResult(name="photo"),
                                @ColumnResult(name="description"),
                                @ColumnResult(name="price"),
                                @ColumnResult(name="down_payment")
                        })
        })
@NamedQueries(
        {
                @NamedQuery(
                        name="FavCarFilter.findFavoritesByUserID",
                        query="select i.inv_id, i.added_on as added_on, i.exp_on as exp_on, i.item_type as item_type, i.stock as stock, c.car_id as car_id, c.plate_no as plate_no, c.brand as brand, c.model as model, c.prod_yr as prod_yr, c.color as color, c.mileage as mileage, c.photo as photo, c.description as description, c.price as price, c.down_payment as down_payment from Inventory i, Favourite f, Car c where f.inv_id=i.inv_id and i.item_type=:filter and i.inv_id=c.inv_id and f.user_id=:userid"
                ),
        }
)

@Entity
public class FavCarResult {

    @Id
    private int inv_id;
    private Date added_on;
    private Date exp_on;
    private String item_type;
    private int stock;
    private int car_id;
    private String plate_no;
    private String brand;
    private String model;
    private int prod_yr;
    private String color;
    private int mileage;
    private String photo;
    private String description;
    private int price;
    private int down_payment;

    public FavCarResult(int inv_id, Date added_on, Date exp_on, String item_type, int stock, int car_id, String plate_no, String brand, String model, int prod_yr, String color, int mileage, String photo, String description, int price, int down_payment) {
        this.inv_id = inv_id;
        this.added_on = added_on;
        this.exp_on = exp_on;
        this.item_type = item_type;
        this.stock = stock;
        this.car_id = car_id;
        this.plate_no = plate_no;
        this.brand = brand;
        this.model = model;
        this.prod_yr = prod_yr;
        this.color = color;
        this.mileage = mileage;
        this.photo = photo;
        this.description = description;
        this.price = price;
        this.down_payment = down_payment;
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

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getPlate_no() {
        return plate_no;
    }

    public void setPlate_no(String plate_no) {
        this.plate_no = plate_no;
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

    public int getProd_yr() {
        return prod_yr;
    }

    public void setProd_yr(int prod_yr) {
        this.prod_yr = prod_yr;
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

    public int getDown_payment() {
        return down_payment;
    }

    public void setDown_payment(int down_payment) {
        this.down_payment = down_payment;
    }
}
