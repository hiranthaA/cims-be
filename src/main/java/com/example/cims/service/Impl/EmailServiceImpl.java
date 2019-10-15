package com.example.cims.service.Impl;

import com.example.cims.Entity.Order_;
import com.example.cims.Entity.User;
import com.example.cims.model.Order;
import com.example.cims.model.OrderItemCar;
import com.example.cims.model.OrderItemPart;
import com.example.cims.model.OrderList;
import com.example.cims.service.EmailService;
import com.example.cims.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private OrderService orderService;

    @Override
    public boolean sendRegistrationSuccessEmail(User user,String role) {
        try{
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Registration");

            if(role.equals("cust")){
                helper.setText("<div style=\"width:400px;background-color: #f3f3f3;\">\n" +
                        "    <div>\n" +
                        "      <h1 style=\"background-color: #f5d103;padding-left:12px;\">AutoMart</h1>\n" +
                        "    </div>\n" +
                        "    <div style=\"padding-left:12px;\">\n" +
                        "        <h2>Hi "+user.getFname()+",</h2>\n" +
                        "        <h3>You have successfully registered to AutoMart.</h3>\n" +
                        "        <h3>Enjoy shopping with us!</h3>\n" +
                        "    </div>\n" +
                        "    <br>\n" +
                        "  </div>", true);
            }
            else if(role.equals("admin")){
                helper.setText("<div style=\"width:400px;background-color: #f3f3f3;\">\n" +
                        "    <div>\n" +
                        "      <h1 style=\"background-color: #f5d103;padding-left:12px;\">AutoMart</h1>\n" +
                        "    </div>\n" +
                        "    <div style=\"padding-left:12px;\">\n" +
                        "        <h2>Hi "+user.getFname()+",</h2>\n" +
                        "        <h3>You have successfully registered to AutoMart as a Admin.</h3>\n" +
                        "    </div>\n" +
                        "    <br>\n" +
                        "  </div>", true);
            }
            javaMailSender.send(msg);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean sendOrderSuccessfulEmail(Order_ orderitem) {

        OrderList orderList = orderService.getOrderDetailsByOrderItem(orderitem);
        int total=0;

        try{

            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(orderList.getOrderList().get(0).getBuyer().getEmail());
            helper.setSubject("Order Receipt");

            String firstpart = "<div style=\"width:400px;background-color: #f3f3f3;\">\n" +
                    "    <div>\n" +
                    "      <h1 style=\"background-color: #f5d103;padding-left:12px;\">AutoMart</h1>\n" +
                    "    </div>\n" +
                    "    <div style=\"padding-left:12px;padding-right:12px;\">\n" +
                    "        <h2>Hi "+orderList.getOrderList().get(0).getBuyer().getFname()+",</h2>\n" +
                    "        <h3>Thank you for the purchase.</h3>\n" +
                    "        <h3>We hope you enjoy!</h3>\n" +
                    "        <hr>\n" +
                    "        <table>" +
                    "           <tr>\n" +
                    "              <th style=\"width:200px;text-align: left;\">Item</th>\n" +
                    "              <th style=\"width:100px\">Quantity</th>\n" +
                    "              <th style=\"width:200px\">Unit Price (Rs)</th>\n" +
                    "            </tr>";
            String loopPart = "";

            for(Order order : orderList.getOrderList()){
                for(OrderItemCar car : order.getItems().getCars()){
                    loopPart+=("<tr>\n" +
                            "              <td style=\"width:200px\">"+car.getBrand()+" "+car.getModel()+"</td>\n" +
                            "              <td style=\"width:100px;\"><center>"+car.getBought_price()+"</center></td>\n" +
                            "              <td style=\"width:200px\"><center>"+car.getBought_quantity()+"</center></td>\n" +
                            "            </tr>");
                    total += car.getBought_price()*car.getBought_quantity();
                }
                for(OrderItemPart part : order.getItems().getParts()){
                    loopPart+=("<tr>\n" +
                            "              <td style=\"width:200px\">"+part.getBrand()+" "+part.getPartname()+"</td>\n" +
                            "              <td style=\"width:100px;\"><center>"+part.getBought_price()+"</center></td>\n" +
                            "              <td style=\"width:200px\"><center>"+part.getBought_quantity()+"</center></td>\n" +
                            "            </tr>");
                    total += part.getBought_price()*part.getBought_quantity();
                }
            }

            String lastPart = "</table>\n" +
                    "        <hr>\n" +
                    "        <h1>Total</h1>\n" +
                    "        <h2>Rs. "+total+"</h2>\n" +
                    "    </div>\n" +
                    "    <br>\n" +
                    "  </div>";

            helper.setText(firstpart+loopPart+lastPart, true);

            javaMailSender.send(msg);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
