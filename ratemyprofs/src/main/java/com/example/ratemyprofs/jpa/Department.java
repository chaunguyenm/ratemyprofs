package com.example.ratemyprofs.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DEPT")
public class Department {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_DEPT")
    int deptID;

    @Column(name="DEPT_NAME")
    String name;
    
    @Column(name="ID_INST")
    int instID;
    
    @Column(name="DEPT_STATUS")
    int status;
}
