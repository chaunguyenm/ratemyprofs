package com.example.ratemyprofs.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_USER")
    int userID;
    
    @Column(name="USER_FIRST_NAME")
    String firstName;
    
    @Column(name="USER_LAST_NAME")
    String lastName;
    
    @Column(name="USER_STATUS")
    String status;
    
    @Column(name="USERNAME")
    String username;
    
    @Column(name="PASSWORD")
    String password;
    
    @Column(name="EMAIL")
    String email;
}
