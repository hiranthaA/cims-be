package com.example.cims.service.Impl;

import com.example.cims.Entity.Cart;
import com.example.cims.model.CartData;
import com.example.cims.model.Response;
import com.example.cims.repository.CartRepository;
import com.example.cims.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;


    @Override
    public ResponseEntity<Response> addToCart(CartData cartdata) {
        Response response = new Response();
        Cart cartItem = new Cart();
        cartItem.setUserid(cartdata.getUser_id());
        cartItem.setInvid(cartdata.getInv_id());
        cartItem.setQuantity(cartdata.getQuantity());

        try{
            cartRepository.save(cartItem);
            response.setMsg("Item Successfully added to cart.");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> removeFromCart(CartData cartdata) {
        Response response = new Response();
        try{
            int returned = cartRepository.deleteFromCart(cartdata.getUser_id(),cartdata.getInv_id());
            if(returned==0){
                response.setMsg("No such item in the cart.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
            else{
                response.setMsg("Item successfully removed from cart.");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        }
        catch(Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
