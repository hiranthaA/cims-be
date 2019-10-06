package com.example.cims.service;

import com.example.cims.model.RegData;
import com.example.cims.model.Response;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity<Response> register(RegData regdata);

    ResponseEntity<Response> getAll();

    ResponseEntity<Response> getUserDetails(int userid);
}
