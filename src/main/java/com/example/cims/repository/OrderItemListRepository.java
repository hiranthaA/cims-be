package com.example.cims.repository;

import com.example.cims.Entity.OrderItemList;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemListRepository extends JpaRepository<OrderItemList,Integer> {
    List<OrderItemList> findAllByOrderid(int orderid);
}
