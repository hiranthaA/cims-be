package com.example.cims.service.Impl;

import com.example.cims.model.User;
import com.example.cims.repository.UserRepository;
import com.example.cims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {

        return userRepository.save(user);
    }
}
