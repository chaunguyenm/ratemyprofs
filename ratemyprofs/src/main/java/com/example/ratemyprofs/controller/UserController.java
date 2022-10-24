package com.example.ratemyprofs.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ratemyprofs.jpa.User;
import com.example.ratemyprofs.service.UserService;

public class UserController {
    
    @Autowired UserService userService;
    
    @GetMapping("/signup")
    public String showSignUp() {
        return "signup";
    }
    
    @GetMapping("/login")
    public String showLogIn() {
        return "login";
    }
    
    @PostMapping("/signup")
    public String signUp(HttpServletRequest request, Model model) throws ParseException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        this.userService.createUser(user);
        
        model.addAttribute("user", user);
        return "signup";
    }
    
    @PostMapping("/login")
    public String logIn(HttpServletRequest request, Model model) throws ParseException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        User user = this.userService.authenticateUser(email, username, password);
        
        model.addAttribute("user", user);
        return "login";
    }

}
