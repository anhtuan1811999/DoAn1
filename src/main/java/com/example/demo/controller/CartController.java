package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.User;
import com.example.demo.other.Other;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Integer idpost){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();


        if (username.equals("anonymousUser")){

            HttpHeaders headers = new HttpHeaders();
            headers.add("Code","anonymous");
            return new ResponseEntity("chưa đăng nhập",headers,HttpStatus.BAD_REQUEST);

        } else {

            User user = userRepository.findUserByUser_name(username);
            System.out.println(user.getFullName());

            int idUser = user.getIdUser();

            Other checkPost = new Other();
            boolean b = checkPost.isPostExist(postRepository.findPostByIdPost(idpost),cartRepository.findAllByIdUser(idUser));

            if (b == true){
                HttpHeaders headers = new HttpHeaders();
                headers.add("code","existed");
                return new ResponseEntity("bài đăng đã tồn tại",headers,HttpStatus.BAD_REQUEST);
            } else {
                Cart cart = new Cart();
                cart.setIdPost(idpost);
                cart.setIdUser(idUser);
                cartRepository.save(cart);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody Integer idpost){
        System.out.println("đã vào");
        cartRepository.deleteCartByIdPost(idpost);
        System.out.println("đã xoá");
        return new ResponseEntity(HttpStatus.OK);
    }
}
