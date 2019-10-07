package com.example.cims.repository;

import com.example.cims.Entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part,Integer> {
    Part findByInvid(int invid);
}
