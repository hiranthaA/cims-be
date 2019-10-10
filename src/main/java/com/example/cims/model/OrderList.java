package com.example.cims.model;

import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private List<Order> orderList;

    public OrderList() {
        this.orderList = new ArrayList<>();
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}