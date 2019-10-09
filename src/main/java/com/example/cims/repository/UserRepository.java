package com.example.cims.repository;

import com.example.cims.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByuserid(int userid);

    @Query("select u.userid, u.title, u.fname, u.lname, u.nic, u.email, u.phone, u.address from User u, Login l where u.userid=l.userId and l.state=:state")
    List<Object[]> findUsersByState(@Param("state") String state);
}
