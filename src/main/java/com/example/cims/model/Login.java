package com.example.cims.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {

    @Id
    private String username;
    private String password;
    private int userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
