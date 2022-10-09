package com.example.ratemyprofs.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROF")
public class Professor {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_PROF", unique=true, nullable=false)
    private int profID;
    
    @Column(name="PROF_FIRST_NAME", nullable=false, length=45)
    private String firstName;
    
    @Column(name="PROF_LAST_NAME", nullable=false, length=45)
    private String lastName;
    
    @Column(name="PROF_STATUS", nullable=false, length=1)
    private String status; 
    
    public Professor() {}

    public Professor(int profID, String firstName, String lastName, String status) {
        super();
        this.profID = profID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public int getProfID() {
        return profID;
    }

    public void setProfID(int profID) {
        this.profID = profID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getName() {
        return firstName + " " + lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
