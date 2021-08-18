package com.example.demo.service.impl;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public void CreateAndSavePostToDB(String city, String district, String ward,
                                      String address, Float area, String description,
                                      MultipartFile file, LocalDateTime date_created,
                                      Integer idUser) {
        Post post = new Post();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")){
            System.out.println("not a valid file");
        }
        try{
            post.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setCity(city);
        post.setDistrict(district);
        post.setWard(ward);
        post.setAddress(address);
        post.setArea(area);
        post.setDescription(description);
        post.setDate_created(date_created);
        post.setUser(userRepository.findUserByIdUser(idUser));

        postRepository.save(post);
    }
}


