package com.example.ratemyprofs.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROF_COURSE")
public class ProfessorCourse {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_PROF_COURSE")
    int profcourseID;
    
    @Column(name="COURSE_ID_COURSE")
    int courseID;
    
    @Column(name="PROF_ID_PROF")
    int profID;
}
