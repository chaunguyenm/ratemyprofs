package com.example.ratemyprofs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.ratemyprofs.jpa.User;
import com.example.ratemyprofs.repository.UserRepository;

public class UserService {
    
    @Autowired
    UserRepository userRepo;
    
    @Transactional(readOnly=true)
    public List<User> listUsers() {
        return this.userRepo.findAll();
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public void createUser(User user) {
        List<User> users = this.listUsers();
        
        List<String> usernames = new ArrayList<String>();
        List<String> emails = new ArrayList<String>();
        users.forEach(u -> {
            usernames.add(u.getUsername());
            emails.add(u.getEmail());
        });
        
        if (usernames.contains(user.getUsername()))
            throw new RuntimeException("Email address is already in use.");
        if (emails.contains(user.getEmail()))
            throw new RuntimeException("Username already exists.");
        
        user.setUserStatus("A");
        this.userRepo.save(user);
    }
    
    @Transactional(readOnly=true)
    private User findUserByEmail(String email) {
        List<User> users = this.listUsers();
        users.removeIf(u -> !u.getUserStatus().equals("A"));
        users.removeIf(u -> !u.getEmail().equals(email));
        
        return users.size()==1?users.get(0):null;
    }
    
    @Transactional(readOnly=true)
    private User findUserByUsername(String username) {
        List<User> users = this.listUsers();
        users.removeIf(u -> !u.getUserStatus().equals("A"));
        users.removeIf(u -> !u.getEmail().equals(username));
        
        return users.size()==1?users.get(0):null;
    }
    
    @Transactional(readOnly=true)
    public User authenticateUser(String email, String username, String password) {
        User user;
        if (email != null) user = this.findUserByEmail(email);
        else user = this.findUserByUsername(username);
        
        return user.getPassword().equals(password)?user:null;
    }
    
}
