package com.example.cims.service.Impl;

import com.example.cims.model.PaymentData;
import com.example.cims.model.Response;
import com.example.cims.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public ResponseEntity<Response> addNewPayment(PaymentData paymentData) {
        System.out.println("asd");
        return null;
    }
}
