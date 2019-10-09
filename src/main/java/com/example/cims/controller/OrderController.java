package com.example.cims.controller;

import com.example.cims.model.Response;
import com.example.cims.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * fetch order history
     *
     * @Param userid : Id of the user where order details to be fetched.
     * @Param filter : Three types (all, cars, parts)
     * @Param from : Data to filter from
     * @Param to : Data to filter until
     */
    @RequestMapping(value = "/get", method= RequestMethod.GET)
    public ResponseEntity<Response> getOrderDetails(@RequestParam(required = false) int userid, @RequestParam(required = true) String filter, @RequestParam(required = true) String from, @RequestParam(required = true) String to){
        return  orderService.getOrderDetails(userid, filter, from, to);
    }

}
