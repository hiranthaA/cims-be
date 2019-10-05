package com.example.cims.controller;

import com.example.cims.model.AuthData;
import com.example.cims.model.RegData;
import com.example.cims.model.Response;
import com.example.cims.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/authenticate", method= RequestMethod.POST)
    public ResponseEntity<Response> authenticate(@RequestBody AuthData authdata){
        return  authService.authenticate(authdata);
    }

}
