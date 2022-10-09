package com.example.ratemyprofs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    
    @GetMapping("/")
    public String init() {
        System.out.println("Showed landing page");
        return "landing";
    }
    
    @GetMapping("/search")
    public String search(@RequestParam(name="profName") String profName,
                         @RequestParam(name="profInst", required=false) String profInst) {
        
        return "search-results";
    }

}

