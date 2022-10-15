package com.example.ratemyprofs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ratemyprofs.jpa.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
