package com.example.ratemyprofs.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
       
        Prof[] names = new Prof[profiles.size()];
        Dept[] depts = new Dept[profiles.size()];
        Inst[] insts = new Inst[profiles.size()];
        double[] scores = new double[profiles.size()];
        for (int i = 0; i < profiles.size(); i++) {
            names[i] = profiles.get(i).getProf();
            depts[i] = profiles.get(i).getDept();
            insts[i] = depts[i].getInst();
            scores[i] = this.ratingService.calculateScoreByProfDept(profiles.get(i));
        }
        
        model.addAttribute("profName", profName);
        model.addAttribute("names", names);
        model.addAttribute("depts", depts);
        model.addAttribute("insts", insts);
        model.addAttribute("scores", scores);
        model.addAttribute("numProfiles", profiles.size());
        
        return "search-results";
    }

}

