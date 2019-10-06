package com.example.cims.service.Impl;

import com.example.cims.Entity.Login;
import com.example.cims.model.RegData;
import com.example.cims.model.Response;
import com.example.cims.Entity.User;
import com.example.cims.repository.LoginRepository;
import com.example.cims.repository.UserRepository;
import com.example.cims.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public ResponseEntity<Response> register(RegData regdata) {

        User user = new User();
        user.setNic(regdata.getNic());
        user.setTitle(regdata.getTitle());
        user.setAddress(regdata.getAddress());
        user.setEmail(regdata.getEmail());
        user.setFname(regdata.getFname());
        user.setLname(regdata.getLname());
        user.setPhone(regdata.getPhone());

        Login login = new Login();
        login.setUsername(regdata.getEmail());
        login.setPassword(regdata.getPassword());
        login.setRole(regdata.getRole());

        Response response = new Response();

        try{
            User user_res = userRepository.save(user);
            login.setUserId(user_res.getUserid());
            Login login_res = loginRepository.save(login);
            response.setMsg("User Successfully Registered.");
            return new ResponseEntity<Response>(response, HttpStatus.CREATED);
        }
        catch(ConstraintViolationException e){
            response.setMsg("Sorry! User for this NIC already exists.");
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> getAll() {
        Response response = new Response();

        try{
            List<User> userlist = userRepository.findAll();
            response.setData(userlist);
            response.setMsg("Users Successfully Retrieved.");
            return new ResponseEntity<Response>(response, HttpStatus.CREATED);
        }
        catch(Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
