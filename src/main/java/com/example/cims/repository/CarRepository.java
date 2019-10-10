package com.example.cims.repository;

import com.example.cims.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
    Car findByInvid(int invid);
    List<Car> findByBrandLikeOrModelLike(String brand,String model);
}
