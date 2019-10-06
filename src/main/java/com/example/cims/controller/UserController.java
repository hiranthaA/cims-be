package com.example.cims.controller;

import com.example.cims.model.RegData;
import com.example.cims.model.Response;
import com.example.cims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method= RequestMethod.POST)
    public ResponseEntity<Response> register(@RequestBody RegData regdata){
        return  userService.register(regdata);
    }

    @RequestMapping(value = "/getall", method= RequestMethod.GET)
    public ResponseEntity<Response> getAll(){
        return  userService.getAll();
    }



}
