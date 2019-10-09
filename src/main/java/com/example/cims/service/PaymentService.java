package com.example.cims.service;

import com.example.cims.model.PaymentData;
import com.example.cims.model.Response;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<Response> addNewPayment(PaymentData paymentData);
}
