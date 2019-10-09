package com.example.cims.model;

public class UserResult {
    private int userid;
    private String title;
    private String fname;
    private String lname;
    private String nic;
    private String email;
    private String phone;
    private String adderss;

    public UserResult(int userid, String title, String fname, String lname, String nic, String email, String phone, String adderss) {
        this.userid = userid;
        this.title = title;
        this.fname = fname;
        this.lname = lname;
        this.nic = nic;
        this.email = email;
        this.phone = phone;
        this.adderss = adderss;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss;
    }
}
