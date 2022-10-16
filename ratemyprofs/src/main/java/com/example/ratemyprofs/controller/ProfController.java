package com.example.ratemyprofs.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ratemyprofs.jpa.Dept;
import com.example.ratemyprofs.jpa.Inst;
import com.example.ratemyprofs.jpa.Prof;
import com.example.ratemyprofs.jpa.ProfDept;
import com.example.ratemyprofs.service.ProfDeptService;
import com.example.ratemyprofs.service.ProfService;
import com.example.ratemyprofs.service.RatingService;

@Controller
public class ProfController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired ProfService profService;
    @Autowired ProfDeptService profDeptService;
    @Autowired RatingService ratingService;
    
    @GetMapping({"/", "/index"})
    public String init() {
        logger.debug("Showed index!");
        return "index";
    }
    
    @PostMapping("/search")
    public String search(HttpServletRequest request, Model model) throws ParseException {
        logger.debug("Showed search results!");
        String profName = request.getParameter("profName");
        List<Prof> profs = this.profService.findByName(profName);
        
        List<ProfDept> profiles = new ArrayList<ProfDept>();
        for (Prof prof:profs) {
            profiles.addAll(profDeptService.listDeptByProf(prof));
        }
        
        double[] scores = new double[profiles.size()];
        for (int i = 0; i < profiles.size(); i++) {
            scores[i] = this.ratingService.calculateScoreByProfDept(profiles.get(i));
        }
        
        model.addAttribute("profiles", profiles);
        model.addAttribute("profName", profName);
        model.addAttribute("scores", scores);
        model.addAttribute("numProfiles", profiles.size());
        
        return "search-results";
    }
    
    @GetMapping("/{idProfDept}")
    public String showProfile(@PathVariable("idProfDept") int idProfDept, Model model) {
        ProfDept profDept = this.profDeptService.findById(idProfDept);
        
        model.addAttribute("profDept", profDept);
        model.addAttribute("ratings", this.ratingService.findRatingsByProfDept(profDept));
        model.addAttribute("score", this.ratingService.calculateScoreByProfDept(profDept));
        model.addAttribute("difficulty", this.ratingService.calculateDifficultyByProfDept(profDept));
        model.addAttribute("retake", this.ratingService.calculateWillRetakeByProfDept(profDept));
        model.addAttribute("courses", this.ratingService.listCourseByProfDept(profDept));
        return "profile";
    }
    
    @GetMapping("/{idProfDept}/{courseCode}")
    public String showProfileWithCourse(@PathVariable("idProfDept") int idProfDept,
                                        @PathVariable("courseCode") String courseCode,
                                        Model model) {
        ProfDept profDept = this.profDeptService.findById(idProfDept);
        
        model.addAttribute("profDept", profDept);
        model.addAttribute("ratings", this.ratingService.findRatingsByProfDept(profDept, courseCode));
        model.addAttribute("score", this.ratingService.calculateScoreByProfDept(profDept, courseCode));
        model.addAttribute("difficulty", this.ratingService.calculateDifficultyByProfDept(profDept, courseCode));
        model.addAttribute("retake", this.ratingService.calculateWillRetakeByProfDept(profDept, courseCode));
        model.addAttribute("courses", this.ratingService.listCourseByProfDept(profDept));
        return "profile";
    }
    

}

