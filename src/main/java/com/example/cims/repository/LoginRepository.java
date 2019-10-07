package com.example.cims.repository;

import com.example.cims.Entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,Integer> {

    Login findByUsername(String username);

    Login findByUserId(int userid);
}
