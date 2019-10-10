package com.example.cims.service.Impl;

import com.example.cims.Entity.Inventory;
import com.example.cims.Entity.OrderItemList;
import com.example.cims.Entity.Order_;
import com.example.cims.Entity.User;
import com.example.cims.model.Response;
import com.example.cims.repository.InventoryRepository;
import com.example.cims.repository.OrderItemListRepository;
import com.example.cims.repository.OrderRepository;
import com.example.cims.repository.UserRepository;
import com.example.cims.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private OrderItemListRepository orderItemListRepository;

    @Override
    public ResponseEntity<Response> getOrderDetails(int userid, String filter, String from, String to) {
        Response response = new Response();
        List<Order_> orderlist;
        List<Inventory> inventoryList;
        try{
            if(userid==0){
                orderlist = orderRepository.findAllByOrderdateBetween(from,to);
            }else{
                User user = userRepository.findByuserid(userid);
                if(user==null){
                    response.setMsg("Sorry! No such user available.");
                    return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
                }
                orderlist = orderRepository.findAllByBuyeridAndOrderdateBetween(userid,from,to);
            }

            for(Order_ order : orderlist){
                List<OrderItemList> itemList = orderItemListRepository.findAllByOrderid(order.getOrderid());
                for (OrderItemList item : itemList){
                    Inventory inventory = inventoryRepository.findByInvid(item.getInvid());
                }
            }

            response.setData(orderlist);
            response.setMsg("Fetching order history successful.");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
        catch (Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
