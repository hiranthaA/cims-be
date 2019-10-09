package com.example.cims.model;

public class PaymentData {
    private String card_type;
    private String card_no;
    private String card_holder;
    private int card_csv;
    private String card_exp;
    private int buyer_id;
    private int[] item_list;
    private int[] quantity_list;
    private int[] per_unit_price;

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public int getCard_csv() {
        return card_csv;
    }

    public void setCard_csv(int card_csv) {
        this.card_csv = card_csv;
    }

    public String getCard_exp() {
        return card_exp;
    }

    public void setCard_exp(String card_exp) {
        this.card_exp = card_exp;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int[] getItem_list() {
        return item_list;
    }

    public void setItem_list(int[] item_list) {
        this.item_list = item_list;
    }

    public int[] getQuantity_list() {
        return quantity_list;
    }

    public void setQuantity_list(int[] quantity_list) {
        this.quantity_list = quantity_list;
    }

    public String getCard_holder() {
        return card_holder;
    }

    public void setCard_holder(String card_holder) {
        this.card_holder = card_holder;
    }

    public int[] getPer_unit_price() {
        return per_unit_price;
    }

    public void setPer_unit_price(int[] per_unit_price) {
        this.per_unit_price = per_unit_price;
    }
}
