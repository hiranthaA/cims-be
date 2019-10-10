package com.example.cims.service.Impl;

import com.example.cims.Entity.*;
import com.example.cims.model.*;
import com.example.cims.repository.*;
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
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private PartRepository partRepository;

    @Override
    public ResponseEntity<Response> getOrderDetails(int userid, String filter, String from, String to) {
        Response response = new Response();
        List<Order_> orderlist;
        OrderList orderList = new OrderList();
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
                Order o = new Order();
                o.setOrderId(order.getOrderid());
                o.setOrderDate(order.getOrderdate());
                o.setPayId(order.getPayid());

                User buyer = userRepository.findByuserid(order.getBuyerid());
                o.setBuyer(buyer);

                Items items = new Items();

                List<OrderItemList> itemList = orderItemListRepository.findAllByOrderid(order.getOrderid());
                for (OrderItemList item : itemList){
                    Inventory inventory = inventoryRepository.findByInvid(item.getInvid());
                    if(filter.equals("all")){
                        if(inventory.getItemtype().equals("car")){
                            Car car = carRepository.findByInvid(inventory.getInvid());
                            OrderItemCar orderCar = mapCarToOrderItemCar(car);
                            orderCar.setBought_price(item.getPriceperunit());
                            orderCar.setBought_quantity(item.getQuantity());
                            items.getCars().add(orderCar);
                        }
                        else if(inventory.getItemtype().equals("part")){
                            Part part = partRepository.findByInvid(inventory.getInvid());
                            OrderItemPart orderPart = mapPartToOrderItemPart(part);
                            orderPart.setBought_price(item.getPriceperunit());
                            orderPart.setBought_quantity(item.getQuantity());
                            items.getParts().add(orderPart);
                        }
                    }
                    else if(filter.equals("cars")){
                        if(inventory.getItemtype().equals("car")){
                            Car car = carRepository.findByInvid(inventory.getInvid());
                            OrderItemCar orderCar = mapCarToOrderItemCar(car);
                            orderCar.setBought_price(item.getPriceperunit());
                            orderCar.setBought_quantity(item.getQuantity());
                            items.getCars().add(orderCar);
                        }
                    }
                    else if(filter.equals("parts")){
                        if(inventory.getItemtype().equals("part")){
                            Part part = partRepository.findByInvid(inventory.getInvid());
                            OrderItemPart orderPart = mapPartToOrderItemPart(part);
                            orderPart.setBought_price(item.getPriceperunit());
                            orderPart.setBought_quantity(item.getQuantity());
                            items.getParts().add(orderPart);
                        }
                    }
                    else{
                        response.setMsg("Sorry! Invalid filter.");
                        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
                    }
                }
                o.setItems(items);
                orderList.getOrderList().add(o);
            }


            response.setData(orderList);
            response.setMsg("Fetching order history successful.");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private OrderItemCar mapCarToOrderItemCar(Car car){
        OrderItemCar orderItemCar = new OrderItemCar();
        orderItemCar.setBrand(car.getBrand());
        orderItemCar.setCarid(car.getCarid());
        orderItemCar.setColor(car.getColor());
        orderItemCar.setDescription(car.getDescription());
        orderItemCar.setDownpayment(car.getDownpayment());
        orderItemCar.setInvid(car.getInvid());
        orderItemCar.setMileage(car.getMileage());
        orderItemCar.setModel(car.getModel());
        orderItemCar.setPhoto(car.getPhoto());
        orderItemCar.setPlateno(car.getPlateno());
        orderItemCar.setPrice(car.getPrice());
        orderItemCar.setProdyr(car.getProdyr());
        return orderItemCar;
    }

    private OrderItemPart mapPartToOrderItemPart(Part part){
        OrderItemPart orderItemPart = new OrderItemPart();
        orderItemPart.setBrand(part.getBrand());
        orderItemPart.setDescription(part.getDescription());
        orderItemPart.setInvid(part.getInvid());
        orderItemPart.setPartid(part.getPartid());
        orderItemPart.setPartname(part.getPartname());
        orderItemPart.setPhoto(part.getPhoto());
        orderItemPart.setPrice(part.getPrice());
        return  orderItemPart;
    }
}