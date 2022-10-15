package com.example.ratemyprofs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ratemyprofs.jpa.ProfCourse;

public interface ProfCourseRepository extends JpaRepository<ProfCourse, Integer> {

}
