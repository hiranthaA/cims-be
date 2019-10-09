package com.example.cims.Entity;

import javax.persistence.*;

@Entity
@IdClass(OrderItemListId.class)
@Table(name = "order_item_list")
public class OrderItemList {

    @Id
    @Column(name = "order_id")
    private int orderid;
    @Id
    @Column(name = "inv_id")
    private int invid;
    private int quantity;
    @Column(name = "price_per_unit")
    private int priceperunit;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getInvid() {
        return invid;
    }

    public void setInvid(int invid) {
        this.invid = invid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPriceperunit() {
        return priceperunit;
    }

    public void setPriceperunit(int priceperunit) {
        this.priceperunit = priceperunit;
    }
}