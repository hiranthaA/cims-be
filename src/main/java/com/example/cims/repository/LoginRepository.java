package com.example.cims.repository;

import com.example.cims.Entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login,Integer> {

    Login findByUsername(String username);
}
