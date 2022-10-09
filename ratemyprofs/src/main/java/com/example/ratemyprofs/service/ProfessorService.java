package com.example.ratemyprofs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ratemyprofs.jpa.Professor;
import com.example.ratemyprofs.repository.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository profRepo;
    
//    @Transactional(readOnly = true)
//    public List<Professor> findByName(String name) {
//        List<Professor> profs = this.profRepo.findAll();
//
//        profs.removeIf(p -> p.getStatus().equals("I"))
//                .removeIf(p -> !p.getName().contains(name));
//
//        return profs;
//    }
}
