package com.example.ratemyprofs.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COURSE")
public class Course {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_COURSE")
    int courseID;

    @Column(name="ID_DEPT")
    int deptID;
    
    @Column(name="COURSE_STAUS")
    String status;
    
    @Column(name="COURSE_NAME")
    String name;
    
    @Column(name="COURSE_CODE")
    String code;
}
