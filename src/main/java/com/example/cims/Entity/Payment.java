package com.example.cims.Entity;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pay_id")
    private int payid;
    @Column(name = "card_type")
    private String cardtype;
    @Column(name = "card_no")
    private String cardno;
    @Column(name = "card_owner")
    private String cardowner;
    @Column(name = "card_csv")
    private int cardcsv;
    @Column(name = "card_exp_date")
    private String cardexpdate;

    public int getPayid() {
        return payid;
    }

    public void setPayid(int payid) {
        this.payid = payid;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCardowner() {
        return cardowner;
    }

    public void setCardowner(String cardowner) {
        this.cardowner = cardowner;
    }

    public int getCardcsv() {
        return cardcsv;
    }

    public void setCardcsv(int cardcsv) {
        this.cardcsv = cardcsv;
    }

    public String getCardexpdate() {
        return cardexpdate;
    }

    public void setCardexpdate(String cardexpdate) {
        this.cardexpdate = cardexpdate;
    }
}