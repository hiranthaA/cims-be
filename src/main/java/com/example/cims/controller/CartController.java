package com.example.cims.controller;

import com.example.cims.model.CartData;
import com.example.cims.model.Response;
import com.example.cims.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * add item to cart
     */
    @RequestMapping(value = "/add", method= RequestMethod.POST)
    public ResponseEntity<Response> addToCart(@RequestBody CartData cartdata){
        return cartService.addToCart(cartdata);
    }

    /**
     * remove a item from cart
     */
    @RequestMapping(value = "/remove", method= RequestMethod.POST)
    public ResponseEntity<Response> removeFromCart(@RequestBody CartData cartdata){
        return  cartService.removeFromCart(cartdata);
    }

}
