package com.example.cims.controller;

import com.example.cims.model.User;
import com.example.cims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method= RequestMethod.POST)
    public User addUser(@RequestBody User user){
        return  userService.addUser(user);
    }


}
