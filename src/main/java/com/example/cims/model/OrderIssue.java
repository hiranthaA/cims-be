package com.example.cims.model;

public class OrderIssue {
    private int item_id;
    private int q_available;
    private String state;
    private int issue_no;
    private String msg;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getQ_available() {
        return q_available;
    }

    public void setQ_available(int q_available) {
        this.q_available = q_available;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getIssue_no() {
        return issue_no;
    }

    public void setIssue_no(int issue_no) {
        this.issue_no = issue_no;
    }
}