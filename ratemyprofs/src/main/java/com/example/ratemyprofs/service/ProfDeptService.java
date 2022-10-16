package com.example.ratemyprofs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ratemyprofs.jpa.Prof;
import com.example.ratemyprofs.jpa.ProfDept;
import com.example.ratemyprofs.repository.ProfDeptRepository;

@Service
public class ProfDeptService {
    
    @Autowired
    ProfDeptRepository profDeptRepo;
    
    @Transactional(readOnly=true)
    public ProfDept findById(int idProfDept) {
        return this.profDeptRepo.findById(Integer.valueOf(idProfDept)).get();
    }
    
    @Transactional(readOnly=true) 
    public List<ProfDept> listDeptByProf(Prof prof) {
        List<ProfDept> profDepts = this.profDeptRepo.findAll();
        profDepts.removeIf(pd -> pd.getProf().getIdProf()!=prof.getIdProf());
        return profDepts;
    }

}
