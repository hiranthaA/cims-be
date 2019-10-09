package com.example.cims.service;

import com.example.cims.model.Response;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<Response> getOrderDetails(int userid, String filter, String from, String to);
}
