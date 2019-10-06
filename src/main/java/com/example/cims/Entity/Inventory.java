package com.example.cims.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inv_id")
    private int invid;
    @Column(name = "added_on")
    private String addedon;
    @Column(name = "exp_on")
    private String expon;
    @Column(name = "item_type")
    private String itemtype;
    private int stock;
    private String state;

    public int getInvid() {
        return invid;
    }

    public void setInvid(int invid) {
        this.invid = invid;
    }

    public String getAddedon() {
        return addedon;
    }

    public void setAddedon(String addedon) {
        this.addedon = addedon;
    }

    public String getExpon() {
        return expon;
    }

    public void setExpon(String expon) {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
