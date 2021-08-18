package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User getUserById(int id);

    User getUserById2(int id, String lastName);

    boolean checkLoginForm(String userName, String pass);
}