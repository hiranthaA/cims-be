package com.example.cims.repository;

import com.example.cims.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {
    Car findByInvid(int invid);
}
