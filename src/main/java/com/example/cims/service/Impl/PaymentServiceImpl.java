package com.example.cims.service.Impl;

import com.example.cims.Entity.Inventory;
import com.example.cims.Entity.OrderItemList;
import com.example.cims.Entity.Order_;
import com.example.cims.Entity.Payment;
import com.example.cims.model.OrderIssue;
import com.example.cims.model.PaymentData;
import com.example.cims.model.Response;
import com.example.cims.repository.InventoryRepository;
import com.example.cims.repository.OrderItemListRepository;
import com.example.cims.repository.OrderRepository;
import com.example.cims.repository.PaymentRepository;
import com.example.cims.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemListRepository orderItemListRepository;
    @Autowired
    private InventoryRepository inventoryRepository;


    @Override
    public ResponseEntity<Response> addNewPayment(PaymentData paymentData) {
        Response response = new Response();
        Payment payment = new Payment();
        Order_ order = new Order_();
        List<OrderItemList> orderItemList = new ArrayList<>();
        String currDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        int itemList[] = paymentData.getItem_list();
        int quantityList[] = paymentData.getQuantity_list();
        int perUnitPriceList[] = paymentData.getPer_unit_price();

        List<OrderIssue> orderIssueList = new ArrayList<>();

        payment.setCardcsv(paymentData.getCard_csv());
        payment.setCardexpdate(paymentData.getCard_exp());
        payment.setCardno(paymentData.getCard_no());
        payment.setCardholder(paymentData.getCard_holder());
        payment.setCardtype(paymentData.getCard_type());

        order.setBuyerid(paymentData.getBuyer_id());
        order.setOrderdate(currDateTime);

        try{

            //Validate the order quantity
            for(int i=0; i<itemList.length;i++){
                Inventory inventory = inventoryRepository.findByInvid(itemList[i]);
                //issue 1
                if(inventory==null){
                    OrderIssue orderIssue = new OrderIssue();
                    orderIssue.setItem_id(itemList[i]);
                    orderIssue.setIssue_no(1);
                    orderIssue.setMsg("No such item in the inventory.");
                    orderIssueList.add(orderIssue);
                }
                //issue 2
                else if(inventory.getState().equals("deleted")){
                    OrderIssue orderIssue = new OrderIssue();
                    orderIssue.setState(inventory.getState());
                    orderIssue.setItem_id(itemList[i]);
                    orderIssue.setIssue_no(2);
                    orderIssue.setMsg("Item is no longer available for sale.");
                    orderIssueList.add(orderIssue);
                }
                //issue 3
                else if(inventory.getState().equals("na")){
                    OrderIssue orderIssue = new OrderIssue();
                    orderIssue.setState(inventory.getState());
                    orderIssue.setItem_id(itemList[i]);
                    orderIssue.setIssue_no(3);
                    orderIssue.setQ_available(inventory.getStock());
                    orderIssue.setMsg("Item is out of stock.");
                    orderIssueList.add(orderIssue);
                }
                //issue 4
                else if(inventory.getStock()<quantityList[i]){
                    OrderIssue orderIssue = new OrderIssue();
                    orderIssue.setState(inventory.getState());
                    orderIssue.setItem_id(itemList[i]);
                    orderIssue.setIssue_no(4);
                    orderIssue.setQ_available(inventory.getStock());
                    orderIssue.setMsg("Less quantity available in the stock.");
                    orderIssueList.add(orderIssue);
                }
            }

            //If issues found, reject payment
            if(!orderIssueList.isEmpty()){
                response.setData(orderIssueList);
                response.setMsg("Sorry! There are some issues regarding this order.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }

            //continue payment process
            Payment pay_result = paymentRepository.save(payment);
            order.setPayid(pay_result.getPayid());

            Order_ order_result = orderRepository.save(order);

            for(int i=0; i<itemList.length;i++){
                OrderItemList orderitem = new OrderItemList();
                orderitem.setOrderid(order_result.getOrderid());
                orderitem.setInvid(itemList[i]);
                orderitem.setQuantity(quantityList[i]);
                orderitem.setPriceperunit(perUnitPriceList[i]);
                orderItemList.add(orderitem);
            }
            orderItemListRepository.saveAll(orderItemList);

            //deduct quantity from db
            for(int i=0; i<itemList.length;i++){
                Inventory inventory = inventoryRepository.findByInvid(itemList[i]);
                inventoryRepository.updateStock(itemList[i],inventory.getStock()-quantityList[i],(inventory.getStock()-quantityList[i]==0?"na":"available"));
            }

            //response.setData(pay_result);
            response.setMsg("Payment completed successfully.");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}