package com.example.cims.repository;

import com.example.cims.Entity.Order_;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order_,Integer> {

    List<Order_> findAllByBuyerid(int buyerid);
    List<Order_> findAllByBuyeridAndOrderdateBetween(int buyerid,String from,String to);
    List<Order_> findAllByOrderdateBetween(String from,String to);
    Order_ findByOrderid(int orderid);
}
