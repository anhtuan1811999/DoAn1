package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class Redirect {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartService cartService;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/login1")
    public String moveToLoginPage(){return "login1";}

    @GetMapping("/register")
    public String getRegister() {
        System.out.println("Enter register");
        return "register";
    }

    @GetMapping("/user")
    public String user(Model model, HttpSession session, Principal principal){
        String username = principal.getName();
        int userId = userRepository.findUserByUser_name(username).getIdUser();
        model.addAttribute("username",username);
        session.setAttribute("username",username);

        List<Post> posts = postRepository.findAllByIdUser(userId);
        model.addAttribute("posts",posts);
        return "user";
    }


    @GetMapping("/update-user")
    public String updateUser(HttpSession session, Model model, Principal principal){
        System.out.println(session.getAttribute("username"));

         User user = userRepository.findUserByUser_name(principal.getName());
         model.addAttribute("user",user);
        return "updateUser";
    }

    @GetMapping("/addPosting")
    public String addPosting(){
        return "post";
    }

    @ModelAttribute("user")
    public User listUser(){
        return userRepository.findUserByIdUser(9);
    }

    @GetMapping(value = "test")
    public ModelAndView test(@ModelAttribute(name = "user") User user){
        ModelAndView model = new ModelAndView();

        model.addObject("message","This is ModelandView");
        model.setViewName("test");

        return model;
    }

    @GetMapping("/cart")
    public String Cart(Model model, Principal principal){
        String username = principal.getName();
        int userId = userRepository.findUserByUser_name(username).getIdUser();
        List<Post> posts = cartService.findAllPostByIdUser(userId);
        model.addAttribute("posts",posts);
        return "cart";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        List<Post> posts = postRepository.findAllByStatus(0);
        model.addAttribute("posts",posts);
        return "admin";
    }

    @GetMapping("/admin-userlists")
    public String adminUserLists(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "admin-userlists";
    }
}
