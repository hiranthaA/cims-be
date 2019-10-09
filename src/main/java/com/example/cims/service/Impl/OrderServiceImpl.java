package com.example.cims.service.Impl;

import com.example.cims.model.Response;
import com.example.cims.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public ResponseEntity<Response> getOrderDetails(int userid, String filter, String from, String to) {


        return null;
    }
}
