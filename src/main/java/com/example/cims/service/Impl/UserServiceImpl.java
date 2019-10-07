package com.example.cims.service.Impl;

import com.example.cims.Entity.Login;
import com.example.cims.model.RegData;
import com.example.cims.model.Response;
import com.example.cims.Entity.User;
import com.example.cims.model.UserDataUpdated;
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
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
        catch(Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> getUserDetails(int userid) {
        Response response = new Response();

        try{
            User user = userRepository.findByuserid(userid);
            if(user!=null){
                response.setData(user);
                response.setMsg("User Successfully Retrieved.");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
            else{
                response.setMsg("User Not Found.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> updateExistingUser(UserDataUpdated updatedData) {
        Response response = new Response();
        User updatedUser = new User();

        try{
            User user = userRepository.findByuserid(updatedData.getUserid());
            if(user!=null){

                updatedUser.setUserid(user.getUserid());
                updatedUser.setNic(updatedData.getNic());
                updatedUser.setTitle(updatedData.getTitle());
                updatedUser.setFname(updatedData.getFname());
                updatedUser.setLname(updatedData.getLname());
                updatedUser.setPhone(updatedData.getPhone());
                updatedUser.setAddress(updatedData.getAddress());
                updatedUser.setEmail(user.getEmail());

                User result = userRepository.save(updatedUser);

                response.setData(result);
                response.setMsg("User Successfully Updated.");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
            else{
                response.setMsg("User Not Found.");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            response.setMsg("Sorry! An Exception Occured.");
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
