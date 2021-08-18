package com.example.demo.controller;

import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;
import com.example.demo.other.CheckAccount;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    //xử lý sau khi đăng kí
    @PostMapping("/HandleRegister")
    public ResponseEntity<?> HandleRegister(
            @RequestBody UserDAO userDAO
    ){
        System.out.println("Register Controller");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setFullName(userDAO.getFull_name());
        user.setDate(userDAO.getDate());
        user.setAddress(userDAO.getAddress());
        user.setEmail(userDAO.getEmail());
        user.setPhoneNumber(userDAO.getPhone_number());
        user.setUser_name(userDAO.getUser_name());
        user.setPassword(passwordEncoder.encode(userDAO.getPassword()));
        user.setRole("ROLE_USER");

        List<String> listusername = userRepository.returnUsernameFromUser();

        CheckAccount checkAccount = new CheckAccount();
        boolean b = checkAccount.checkAccExist(userDAO.getUser_name(),listusername);
        if (b == false){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } else {
            userRepository.save(user);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }

    @PostMapping("/update")
    public void updateUserInfor(HttpServletResponse response, HttpServletRequest request, Principal principal) throws IOException {

        User user = userRepository.findUserByUser_name(principal.getName());

        System.out.println("this is update");
        String fullname = request.getParameter("fullname");
        Date date = Date.valueOf(request.getParameter("date"));
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phonenumber");
        String email = request.getParameter("email");

        user.setFullName(fullname);
        user.setDate(date);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);

        userRepository.save(user);

        response.sendRedirect("/update-user");


    }

}