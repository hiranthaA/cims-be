package com.example.cims.controller;

import com.example.cims.model.RegData;
import com.example.cims.model.Response;
import com.example.cims.model.UserDataUpdated;
import com.example.cims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * register a new user
     */
    @RequestMapping(value = "/register", method= RequestMethod.POST)
    public ResponseEntity<Response> register(@RequestBody RegData regdata){
        return  userService.register(regdata);
    }

    /**
     * fetch all users
     */
    @RequestMapping(value = "/getall", method= RequestMethod.GET)
    public ResponseEntity<Response> getAll(){
        return  userService.getAll();
    }

    /**
     * fetch a specific users
     *
     * @Param userid User id of a specific user
     */
    @RequestMapping(value = "/get", method= RequestMethod.GET)
    public ResponseEntity<Response> getUserDetails(@RequestParam int userid){
        return  userService.getUserDetails(userid);
    }

    /**
     * update a existing user
     */
    @RequestMapping(value = "/update", method= RequestMethod.POST)
    public ResponseEntity<Response> updateExistingUser(@RequestBody UserDataUpdated updatedData){
        return  userService.updateExistingUser(updatedData);
    }

}
