package com.example.ratemyprofs.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROF_DEPT")
public class ProfessorDepartment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_PROF_DEPT")
    int profdeptID;
    
    @Column(name="DEPT_ID_DEPT")
    int deptID;
    
    @Column(name="PROF_ID_PROF")
    int profID;
}
