package com.example.cims.controller;

import com.example.cims.model.PaymentData;
import com.example.cims.model.Response;
import com.example.cims.service.EmailService;
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

    @Autowired
    private EmailService emailService;

    /**
     * pay for a new order
     */
    @RequestMapping(value = "/payorder", method= RequestMethod.POST)
    public ResponseEntity<Response> addnewPayment(@RequestBody PaymentData paymentData){
        return  paymentService.addNewPayment(paymentData);
    }

    @RequestMapping(value = "/sendmail", method= RequestMethod.GET)
    public boolean sendEmail(){
        return emailService.testSendRegistrationSuccessEmail("adfsfd");
    }
}
