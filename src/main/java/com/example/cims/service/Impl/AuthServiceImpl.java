package com.example.cims.service.Impl;

import com.example.cims.Entity.Login;
import com.example.cims.model.AuthData;
import com.example.cims.model.Response;
import com.example.cims.repository.LoginRepository;
import com.example.cims.repository.UserRepository;
import com.example.cims.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Response> authenticate(AuthData authdata) {
        Response response = new Response();
        try {
            Login res_login = loginRepository.findByUsername(authdata.getUsername());

            if (res_login==null) {
                response.setMsg("Sorry! No such user available.");
                return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
            }
            else if(validatePassword(authdata.getPassword(),res_login.getPassword())){
                response.setMsg("Successfully Authenticated.");
                response.setData(userRepository.findById(res_login.getUserId()));
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
            else{
                response.setMsg("Sorry! Invalid Password.");
                return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validatePassword( String req_pw, String db_pw){
        if(req_pw.equals(db_pw)){
           return true;
        }
        return false;
    }
}
