package com.example.ratemyprofs.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INST")
public class Institution {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_INST")
    int instID;
    
    @Column(name="INST_NAME")
    String name;
    
    @Column(name="INST_STATUS")
    String status;

}
