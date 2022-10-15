package com.example.ratemyprofs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ratemyprofs.jpa.ProfDept;
import com.example.ratemyprofs.jpa.Rating;
import com.example.ratemyprofs.repository.RatingRepository;

@Service
public class RatingService {
    
    @Autowired
    RatingRepository ratingRepo;
    
    @Transactional(readOnly=true)
    public double calculateScoreByProfDept(ProfDept profDept) {
        List<Rating> ratings = this.ratingRepo.findAll();
        ratings.removeIf(r -> r.getDept().getIdDept()!=profDept.getDept().getIdDept() 
                || r.getProf().getIdProf()!=profDept.getProf().getIdProf());
        double total = 0;
        for (Rating rating:ratings) {
            total += rating.getOverallScore();
        }
        return total/ratings.size();
    }

}
