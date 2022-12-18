package com.example.ratemyprofs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.ratemyprofs.jpa.User;
import com.example.ratemyprofs.repository.UserRepository;
import com.example.ratemyprofs.system.UserAlreadyExistsException;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepo;
    
    @Transactional(readOnly=true)
    public List<User> listUsers() {
        return this.userRepo.findAll();
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public void createUser(User user) throws UserAlreadyExistsException {        
        if (findUserByEmail(user.getEmail()) != null)
            throw new UserAlreadyExistsException("Email address is already in use.");
        if (findUserByUsername(user.getUsername()) != null)
            throw new UserAlreadyExistsException("Username already exists.");
        
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
    public User findUserByUsername(String username) {
        List<User> users = this.listUsers();
        users.removeIf(u -> !u.getUserStatus().equals("A"));
        users.removeIf(u -> !u.getUsername().equals(username));
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
