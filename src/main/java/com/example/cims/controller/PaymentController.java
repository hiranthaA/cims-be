package com.example.cims.controller;

import com.example.cims.model.PaymentData;
import com.example.cims.model.Response;
import com.example.cims.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * pay for a new order
     */
    @RequestMapping(value = "/payorder", method= RequestMethod.POST)
    public ResponseEntity<Response> addnewPayment(@RequestBody PaymentData paymentData){
        return  paymentService.addNewPayment(paymentData);
    }
}
