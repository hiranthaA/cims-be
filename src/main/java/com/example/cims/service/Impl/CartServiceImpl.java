package com.example.cims.service.Impl;

import com.example.cims.Entity.Cart;
import com.example.cims.Entity.Inventory;
import com.example.cims.model.*;
import com.example.cims.repository.CartRepository;
import com.example.cims.repository.InventoryRepository;
import com.example.cims.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private InventoryRepository inventoryRepository;


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

    @Override
    public ResponseEntity<Response> getAllCartItems(int userid) {
        Response response = new Response();
        CarInventoryResultExt carInventoryResultExt;
        PartInventoryResultExt partInventoryResultExt;
        List<CarInventoryResult> carInventoryResultList = new ArrayList<>();
        List<PartInventoryResult> partInventoryResultList = new ArrayList<>();
        InventoryListFilter inventoryListFilter = new InventoryListFilter();

        try{
            List<Cart> cartList = cartRepository.findAllByUserid(userid);

            for(Cart cItem : cartList){
                Inventory item = inventoryRepository.findByInvid(cItem.getInvid());
                if(item==null){
                    response.setMsg("Sorry! Invalid inventory item found.");
                    return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                else if(item.getItemtype().equals("car")){
                    if(item.getState().equals("deleted")){

                    }
                    else{
                        Object[] car = inventoryRepository.getInventoryCar("car",cItem.getInvid()).get(0);
                        carInventoryResultExt = new CarInventoryResultExt((int)car[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[1]),(car[2]==null)? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)car[2]),(String)car[3],(int)car[4],(String)car[5],(int)car[6],(String)car[7],(String)car[8],(String)car[9],(int)car[10],(String)car[11],(int)car[12],(String)car[13],(String)car[14],(int)car[15],(int)car[16],cItem.getQuantity());
                        carInventoryResultList.add(carInventoryResultExt);
                    }
                }
                else if(item.getItemtype().equals("part")){
                    if(item.getState().equals("deleted")){

                    }
                    else {
                        Object[] part = inventoryRepository.getInventoryPart("part", cItem.getInvid()).get(0);
                        partInventoryResultExt = new PartInventoryResultExt((int) part[0], new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) part[1]), (part[2] == null) ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) part[2]), (String) part[3], (int) part[4], (String) part[5], (int) part[6], (String) part[7], (String) part[8], (String) part[9], (String) part[10], (int) part[11],cItem.getQuantity());
                        partInventoryResultList.add(partInventoryResultExt);
                    }
                }
            }
            inventoryListFilter.setCars(carInventoryResultList);
            inventoryListFilter.setParts(partInventoryResultList);
            response.setData(inventoryListFilter);
            response.setMsg("Cart items successfully fetched.");
            return new ResponseEntity<Response>(response, HttpStatus.OK);

        }
        catch (Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
