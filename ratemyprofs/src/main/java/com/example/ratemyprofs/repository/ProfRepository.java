package com.example.ratemyprofs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ratemyprofs.jpa.Prof;

public interface ProfRepository extends JpaRepository<Prof, Integer> {

}
