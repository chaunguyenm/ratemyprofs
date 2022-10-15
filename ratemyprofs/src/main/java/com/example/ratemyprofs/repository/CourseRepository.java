package com.example.ratemyprofs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ratemyprofs.jpa.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
