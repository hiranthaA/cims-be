package com.example.cims.service;

import com.example.cims.model.CartData;
import com.example.cims.model.Response;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<Response> addToCart(CartData cartdata);

    ResponseEntity<Response> removeFromCart(CartData cartdata);

    ResponseEntity<Response> getAllCartItems(int userid);

}
