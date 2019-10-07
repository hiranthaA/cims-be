package com.example.cims.service;

import com.example.cims.model.AuthData;
import com.example.cims.model.Password;
import com.example.cims.model.Response;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<Response> authenticate(AuthData authdata);

    ResponseEntity<Response> changePassword(Password password);
}
