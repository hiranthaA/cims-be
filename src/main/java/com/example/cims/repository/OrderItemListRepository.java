package com.example.cims.repository;

import com.example.cims.Entity.OrderItemList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemListRepository extends JpaRepository<OrderItemList,Integer> {
}
