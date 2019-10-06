package com.example.cims.Entity;

import java.io.Serializable;

public class CartId implements Serializable {
    private int userid;
    private int invid;

    public CartId(){};

    public CartId(int userid, int invid) {
        this.userid = userid;
        this.invid = invid;
    }
}
