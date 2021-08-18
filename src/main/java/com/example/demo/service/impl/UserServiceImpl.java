package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User getUserById(int id) {
        User user = userRepository.findUserByIdUser(id);
        return user;
    }

    @Override
    public User getUserById2(int id,String lastName) {

        User user = userRepository.returnUserByIdAndLastname(id,lastName);
        return user;
    }

    @Override
    public boolean checkLoginForm(String userName, String pass) {
        User user = userRepository.findUserByUser_nameAndPassword(userName,pass);
        if (user==null){
            return false;
        } else {
            return true;
        }
    }
}
