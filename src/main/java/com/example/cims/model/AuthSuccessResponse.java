package com.example.cims.model;

public class AuthSuccessResponse {
    private int userid;
    private String nic;
    private String title;
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String address;
    private String role;

    public AuthSuccessResponse(int userid, String nic, String title, String fname, String lname, String email, String phone, String address, String role) {
        this.userid = userid;
        this.nic = nic;
        this.title = title;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
