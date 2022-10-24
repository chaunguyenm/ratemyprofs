package com.example.ratemyprofs.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ratemyprofs.jpa.Prof;
import com.example.ratemyprofs.jpa.ProfDept;
import com.example.ratemyprofs.jpa.Rating;
import com.example.ratemyprofs.service.CourseService;
import com.example.ratemyprofs.service.ProfDeptService;
import com.example.ratemyprofs.service.ProfService;
import com.example.ratemyprofs.service.RatingService;

@Controller
public class ProfController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired ProfService profService;
    @Autowired ProfDeptService profDeptService;
    @Autowired RatingService ratingService;
    @Autowired CourseService courseService;
    
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
        
        // get professors with matching name
        List<ProfDept> profiles = new ArrayList<ProfDept>();
        for (Prof prof:profs) {
            profiles.addAll(profDeptService.listDeptByProf(prof));
        }
        
        // calculate professors' scores
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
        
        // sort ratings from newest to oldest
        List<Rating> ratings = this.ratingService.findRatingsByProfDept(profDept);
        ratings.sort((r1, r2) -> r2.getCreated().compareTo(r1.getCreated()));
        model.addAttribute("ratings", ratings);
        
        model.addAttribute("score", this.ratingService.calculateScoreByProfDept(profDept));
        model.addAttribute("difficulty", this.ratingService.calculateDifficultyByProfDept(profDept));
        model.addAttribute("retake", this.ratingService.calculateWillRetakeByProfDept(profDept));
        model.addAttribute("courses", this.ratingService.listCourseByProfDept(profDept));
        return "profile";
    }
    
    @GetMapping("/{idProfDept}/{idCourse}")
    public String showProfileWithCourse(@PathVariable("idProfDept") int idProfDept,
                                        @PathVariable("idCourse") String idCourse,
                                        Model model) {
        ProfDept profDept = this.profDeptService.findById(idProfDept);
        
        model.addAttribute("profDept", profDept);
        
        // sort ratings from newest to oldest
        List<Rating> ratings = this.ratingService.findRatingsByProfDept(profDept, idCourse);
        ratings.sort((r1, r2) -> r2.getCreated().compareTo(r1.getCreated()));
        model.addAttribute("ratings", ratings);
        
        model.addAttribute("score", this.ratingService.calculateScoreByProfDept(profDept, idCourse));
        model.addAttribute("difficulty", this.ratingService.calculateDifficultyByProfDept(profDept, idCourse));
        model.addAttribute("retake", this.ratingService.calculateWillRetakeByProfDept(profDept, idCourse));
        model.addAttribute("courses", this.ratingService.listCourseByProfDept(profDept));
        return "profile";
    }
    
    @GetMapping("/{idProfDept}/rate")
    public String showRate(@PathVariable("idProfDept") int idProfDept, Model model) {
        ProfDept profDept = this.profDeptService.findById(idProfDept);
        
        model.addAttribute("profDept", profDept);
        return "rate";
    }
    
    @PostMapping("/{idProfDept}/rate")
    public String rate(@PathVariable("idProfDept") int idProfDept, HttpServletRequest request, Model model) {
        ProfDept profDept = this.profDeptService.findById(idProfDept);
        
        // create new Rating
        Rating rating = new Rating();
        rating.setOverallScore(Integer.parseInt(request.getParameter("overallScore")));
        rating.setDifficultyLevel(Integer.parseInt(request.getParameter("difficultyLevel")));
        rating.setWillRetake(request.getParameter("willRetake").equals("yes")?true:false);
        if (request.getParameter("forCredit") != null) 
            rating.setForCredit(request.getParameter("forCredit").equals("yes")?true:false);
        if (request.getParameter("requireTextbook") != null) 
            rating.setRequireTextbook(request.getParameter("requireTextbook").equals("yes")?true:false);
        if (request.getParameter("requireAttendance") != null) 
            rating.setRequireAttendance(request.getParameter("requireAttendance").equals("yes")?true:false);
        if (request.getParameter("receivedGrade") != null) 
            rating.setReceivedGrade(request.getParameter("receivedGrade"));
        rating.setReview(request.getParameter("review"));
        rating.setProf(profDept.getProf());
        if (request.getParameter("course") != null)
            rating.setCourse(this.courseService.findCourseById(Integer.parseInt(request.getParameter("course"))));
        rating.setDept(profDept.getDept());
        rating.setCourseCode(request.getParameter("courseCode"));
        rating.setCreated(new Date());
        rating.setModified(rating.getCreated());
        rating.setRatingStatus("A");
        rating.setUserIdUser(Integer.parseInt(request.getParameter("idUser")));
        
        // write new rating to database
        this.ratingService.createRating(rating);
        
        
        model.addAttribute("profDept", profDept);
        
        // sort ratings from newest to oldest
        List<Rating> ratings = this.ratingService.findRatingsByProfDept(profDept);
        ratings.sort((r1, r2) -> r2.getCreated().compareTo(r1.getCreated()));
        model.addAttribute("ratings", ratings);
        
        model.addAttribute("score", this.ratingService.calculateScoreByProfDept(profDept));
        model.addAttribute("difficulty", this.ratingService.calculateDifficultyByProfDept(profDept));
        model.addAttribute("retake", this.ratingService.calculateWillRetakeByProfDept(profDept));
        model.addAttribute("courses", this.ratingService.listCourseByProfDept(profDept));
        return "profile";
    }
}

