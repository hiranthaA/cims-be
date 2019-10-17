package com.example.cims.repository;

import com.example.cims.Entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LoginRepository extends JpaRepository<Login,Integer> {

    Login findByUsername(String username);

    Login findByUserId(int userid);

    @Transactional
    @Modifying
    @Query("update Login set state=:state where userId=:userid")
    int updateState(@Param("userid") int userid, @Param("state") String state);
}
