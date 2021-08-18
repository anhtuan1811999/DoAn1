package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/home")
    public String home(Model model, HttpSession session){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        String username = auth.getName();
        if (!username.equals("anonymousUser")){
            session.setAttribute("username",username);
            model.addAttribute("username",username);
        }
        List<Post> posts = postRepository.findAllByStatus(1);
        model.addAttribute("posts",posts);

        return "home";
    }

    @GetMapping("/")
    public void home1(HttpServletResponse response) throws IOException {
        response.sendRedirect("/home");
    }

}
