package com.example.cims.service.Impl;

import com.example.cims.Entity.Login;
import com.example.cims.Entity.User;
import com.example.cims.model.AuthData;
import com.example.cims.model.AuthSuccessResponse;
import com.example.cims.model.Password;
import com.example.cims.model.Response;
import com.example.cims.repository.LoginRepository;
import com.example.cims.repository.UserRepository;
import com.example.cims.service.AuthService;
import org.apache.commons.codec.binary.Base64;
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
            else if(res_login.getState().equals("deleted")){
                response.setMsg("Sorry! User account is no longer available.");
                return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
            }
            else if(validatePassword(authdata.getPassword(),res_login.getPassword())){
                User user = userRepository.findByuserid(res_login.getUserId());
                AuthSuccessResponse asr = new AuthSuccessResponse(user.getUserid(),user.getNic(),user.getTitle(),user.getFname(),user.getLname(),user.getEmail(),user.getPhone(),user.getAddress(),res_login.getRole());
                response.setMsg("Successfully Authenticated.");
                response.setData(asr);
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

    @Override
    public ResponseEntity<Response> changePassword(Password password) {
        Response response = new Response();
        try {

            User user = userRepository.findByuserid(password.getUserid());
            Login oldlogin = loginRepository.findByUserId(password.getUserid());
            Login newLogin = new Login();

            if (user==null) {
                response.setMsg("Sorry! No such user available.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
            else if(validatePassword(password.getOldpw(),oldlogin.getPassword())){
                if(validatePassword(password.getNewpw(),oldlogin.getPassword())){
                    response.setMsg("Sorry! New Password is same as old Password.");
                    return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
                }
                else{
                    newLogin.setUserId(user.getUserid());
                    newLogin.setRole(oldlogin.getRole());
                    newLogin.setUsername(oldlogin.getUsername());
                    newLogin.setState(oldlogin.getState());
                    String encodedPw = new String(Base64.encodeBase64(password.getNewpw().getBytes()));
                    newLogin.setPassword(encodedPw);
                    loginRepository.save(newLogin);
                    response.setMsg("Password Changed Successfully.");
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                }
            }
            else{
                response.setMsg("Sorry! Invalid old password.");
                return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validatePassword( String req_pw, String db_pw){
        String decodedPW = new String(Base64.decodeBase64(db_pw.getBytes()));
        if(req_pw.equals(decodedPW)){
           return true;
        }
        return false;
    }
}
