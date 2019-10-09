package com.example.cims.repository;

import com.example.cims.Entity.Order_;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order_,Integer> {
}
