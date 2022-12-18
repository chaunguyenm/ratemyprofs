package com.example.ratemyprofs.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ratemyprofs.jpa.User;
import com.example.ratemyprofs.service.UserService;
import com.example.ratemyprofs.system.UserAlreadyExistsException;

@Controller
public class UserController {
    
    @Autowired UserService userService;
    
    @GetMapping("/signup")
    public String showSignUp() {
        return "signup";
    }
    
//    @GetMapping("/login")
//    public String showLogIn() {
//        return "login";
//    }
    
    @PostMapping("/signup")
    public String signUp(HttpServletRequest request, Model model) throws ParseException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        User user = new User();
        user.setUserFirstName(firstName);
        user.setUserLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        
        try {
            this.userService.createUser(user);
        } 
        catch (UserAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
        
        model.addAttribute("message", "Registration successful! Please log in.");
        return "login";
    }

}
