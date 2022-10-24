package com.example.ratemyprofs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.ratemyprofs.jpa.Course;
import com.example.ratemyprofs.jpa.ProfDept;
import com.example.ratemyprofs.jpa.Rating;
import com.example.ratemyprofs.repository.RatingRepository;

@Service
public class RatingService {
    
    @Autowired
    RatingRepository ratingRepo;
    
    @Transactional(readOnly=true)
    public List<Rating> findRatingsByProfDept(ProfDept profDept) {
        List<Rating> ratings = this.ratingRepo.findAll();
        ratings.removeIf(r -> r.getDept().getIdDept()!=profDept.getDept().getIdDept() 
                || r.getProf().getIdProf()!=profDept.getProf().getIdProf());
        return ratings;
    }
    
    @Transactional(readOnly=true)
    public List<Rating> findRatingsByProfDept(ProfDept profDept, String idCourse) {
        List<Rating> ratings = this.ratingRepo.findAll();
        ratings.removeIf(r -> r.getDept().getIdDept()!= profDept.getDept().getIdDept() 
                || r.getProf().getIdProf()!=profDept.getProf().getIdProf());
        
        // idCourse == #, then filter ratings with no registered course
        if (idCourse.equals("other")) ratings.removeIf(r -> r.getCourse() != null || r.getCourseCode() == null);
        
        // idCourse is specified, then filter by course.idCourse
        else ratings.removeIf(r -> r.getCourse() == null || r.getCourse().getIdCourse() != Integer.parseInt(idCourse));
        
        return ratings;
    }
    
    @Transactional(readOnly=true)
    public double calculateScoreByProfDept(ProfDept profDept) {
        List<Rating> ratings = this.findRatingsByProfDept(profDept);
        double total = 0;
        for (Rating rating:ratings) {
            total += rating.getOverallScore();
        }
        return total/ratings.size();
    }
    
    @Transactional(readOnly=true)
    public double calculateScoreByProfDept(ProfDept profDept, String idCourse) {
        List<Rating> ratings = this.findRatingsByProfDept(profDept, idCourse);
        double total = 0;
        for (Rating rating:ratings) {
            total += rating.getOverallScore();
        }
        return total/ratings.size();
    }
    
    @Transactional(readOnly=true)
    public double calculateDifficultyByProfDept(ProfDept profDept) {
        List<Rating> ratings = this.findRatingsByProfDept(profDept);
        double total = 0;
        for (Rating rating:ratings) {
            total += rating.getDifficultyLevel();
        }
        return total/ratings.size();
    }
    
    @Transactional(readOnly=true)
    public double calculateDifficultyByProfDept(ProfDept profDept, String idCourse) {
        List<Rating> ratings = this.findRatingsByProfDept(profDept, idCourse);
        double total = 0;
        for (Rating rating:ratings) {
            total += rating.getDifficultyLevel();
        }
        return total/ratings.size();
    }
    
    @Transactional(readOnly=true)
    public double calculateWillRetakeByProfDept(ProfDept profDept) {
        List<Rating> ratings = this.findRatingsByProfDept(profDept);
        ratings.removeIf(r -> r.getWillRetake()==null);
        int count = 0;
        for (Rating rating:ratings) {
            if (rating.getWillRetake() != null && rating.getWillRetake()) count++;
        }
        return count==0?-1:count/ratings.size()*100;
    }
    
    @Transactional(readOnly=true)
    public double calculateWillRetakeByProfDept(ProfDept profDept, String idCourse) {
        List<Rating> ratings = this.findRatingsByProfDept(profDept, idCourse);
        ratings.removeIf(r -> r.getWillRetake()==null);
        int count = 0;
        for (Rating rating:ratings) {
            if (rating.getWillRetake() != null && rating.getWillRetake()) count++;
        }
        return count==0?-1:count/ratings.size()*100;
    }
    
    @Transactional(readOnly=true)
    public List<Course> listCourseByProfDept(ProfDept profDept) {
        Map<String, Course> courses = new HashMap<String, Course>();
        List<Rating> ratings = this.findRatingsByProfDept(profDept);
        for (Rating rating:ratings) {
            if (rating.getCourse() != null) courses.put(rating.getCourse().getCourseCode(), rating.getCourse());
        }
        return new ArrayList<Course>(courses.values());
    }
    
    private void validateRating(Rating rating) {
        
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public void createRating(Rating rating) {
        this.validateRating(rating);
        
        rating.setRatingStatus("A");
        this.ratingRepo.save(rating);
    }

}
