package com.example.ratemyprofs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ratemyprofs.jpa.Course;
import com.example.ratemyprofs.repository.CourseRepository;

@Service
public class CourseService {
    
    @Autowired
    CourseRepository courseRepo;
    
    @Transactional(readOnly=true)
    public Course findCourseById(int idCourse) {
        List<Course> courses = this.courseRepo.findAll();
        courses.removeIf(course -> course.getIdCourse() != idCourse);
        
        return courses.size()==1?courses.get(0):null;
    }

}
