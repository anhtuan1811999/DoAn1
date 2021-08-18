package com.example.demo.service.impl;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Post;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> findAllPostByIdUser(int idUser) {
        List<Cart> cartList = cartRepository.findAllByIdUser(idUser);
        int size = cartList.size();
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i<size; i++){
            Post post = postRepository.findPostByIdPost(cartList.get(i).getIdPost());
            posts.add(post);
        }
        return posts;
    }
}
