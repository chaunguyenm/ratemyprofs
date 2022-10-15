package com.example.ratemyprofs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ratemyprofs.jpa.Dept;
import com.example.ratemyprofs.jpa.Prof;
import com.example.ratemyprofs.repository.ProfRepository;

@Service
public class ProfService {

    @Autowired
    ProfRepository profRepo;
    
    @Transactional(readOnly = true)
    public List<Prof> findByName(String profName) {
        List<Prof> profs = this.profRepo.findAll();
        
        profs.removeIf(p -> p.getProfStatus().equals("I"));
        profs.removeIf(p -> !p.getProfName().toLowerCase().contains(profName.toLowerCase()));
        return profs;
    }
    
    @Transactional(readOnly=true)
    public double calculateScore() {
        return 0;
    }
    
    @Transactional(readOnly=true)
    public List<Dept> listDept(int idProf) {
        return null;
    }
}
