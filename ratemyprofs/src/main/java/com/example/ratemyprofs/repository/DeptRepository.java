package com.example.ratemyprofs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ratemyprofs.jpa.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer> {

}
