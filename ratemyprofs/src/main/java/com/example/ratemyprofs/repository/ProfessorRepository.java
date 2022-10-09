package com.example.ratemyprofs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ratemyprofs.jpa.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
