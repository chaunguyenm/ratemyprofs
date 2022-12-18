package com.example.ratemyprofs.system;

public class UserAlreadyExistsException extends Exception {
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
