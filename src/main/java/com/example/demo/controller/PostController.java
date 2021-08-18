package com.example.demo.controller;


import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.other.Other;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping( path = "/addp",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addp(HttpServletResponse response,
            HttpServletRequest request,
            HttpSession session,
            @RequestParam("file") MultipartFile file,
            Principal principal) throws IOException {

        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String address = request.getParameter("address");
        Float area = Float.parseFloat(request.getParameter("area")) ;
        String description = request.getParameter("description");
        LocalDateTime dateTime = Other.getDateTimeNow();

        System.out.println(dateTime);
        System.out.println(city);

        User user = userRepository.findUserByUser_name(principal.getName());

        Integer idUser = user.getIdUser();

        postService.CreateAndSavePostToDB(city,district,ward,address,area,description,file,dateTime,idUser);

        response.sendRedirect("/user");
    }

    @PostMapping("/approve-post")
    public ResponseEntity approvePost( @RequestBody Integer idpost,HttpServletResponse response) throws IOException {
        postRepository.updateStatusPost(idpost);
        return new ResponseEntity(HttpStatus.OK);
    }

}
