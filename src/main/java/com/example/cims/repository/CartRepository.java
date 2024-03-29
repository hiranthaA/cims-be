package com.example.cims.repository;

import com.example.cims.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    Cart findByUseridAndInvid(int userid, int invid);

    List<Cart> findAllByUserid(int userid);

    @Transactional
    @Modifying
    @Query("delete from Cart c where c.userid=:userid and c.invid=:invid")
    int deleteFromCart(@Param("userid") int userid, @Param("invid") int invid);
}
