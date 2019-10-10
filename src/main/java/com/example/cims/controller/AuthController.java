package com.example.cims.controller;

import com.example.cims.model.AuthData;
import com.example.cims.model.Password;
import com.example.cims.model.Response;
import com.example.cims.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * validate user credentials
     */
    @RequestMapping(value = "/authenticate", method= RequestMethod.POST)
    public ResponseEntity<Response> authenticate(@RequestBody AuthData authdata){
        return  authService.authenticate(authdata);
    }

    /**
     * change password of a existing user
     */
    @RequestMapping(value = "/changepw", method= RequestMethod.POST)
    public ResponseEntity<Response> changePassword(@RequestBody Password password){
        return  authService.changePassword(password);
    }

}
