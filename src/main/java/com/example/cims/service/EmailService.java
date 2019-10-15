package com.example.cims.service;

import com.example.cims.Entity.User;

import java.util.List;

public interface EmailService {
    boolean sendRegistrationSuccessEmail(User user,String role);
    boolean sendOrderSuccessfulEmail(String email, List<Object> orderlist);
    boolean testSendRegistrationSuccessEmail(String email);
}
