package com.example.cims.Entity;

import java.io.Serializable;

public class OrderItemListId implements Serializable {

    private int orderid;
    private int invid;

    public OrderItemListId(){}

    public OrderItemListId(int orderid, int invid) {
        this.orderid = orderid;
        this.invid = invid;
    }
}