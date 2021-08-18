package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CartService {
    List<Post> findAllPostByIdUser(int idUser);
}
