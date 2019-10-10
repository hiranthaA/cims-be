package com.example.cims.repository;

import com.example.cims.Entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartRepository extends JpaRepository<Part,Integer> {
    Part findByInvid(int invid);
    List<Part> findByBrandLikeOrPartnameLike(String brand, String partname);
}
