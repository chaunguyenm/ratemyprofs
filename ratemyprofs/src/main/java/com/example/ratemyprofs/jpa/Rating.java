package com.example.ratemyprofs.jpa;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RATING")
public class Rating {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_RATING")
    int ratingID;
    
    @Column(name="OVERALL_SCORE")
    int overallScore;
    
    @Column(name="DIFFICULTY_LEVEL")
    int difficulty;
    
    @Column(name="WILL_RETAKE")
    boolean willRetake;
    
    @Column(name="FOR_CREDIT")
    boolean forCredit;
    
    @Column(name="REQUIRE_ATTENDANCE")
    boolean requireAttendance;
    
    @Column(name="REQUIRE_TEXTBOOK")
    boolean requireTextbook;
    
    @Column(name="RECEIVED_GRADE")
    String receivedGrade;
    
    @Column(name="REVIEW")
    String review;
    
    @Column(name="COURSE_ID_COURSE")
    int courseID;
    
    @Column(name="PROF_ID_PROF")
    int profID;
    
    @Column(name="DEPT_ID_DEPT")
    int deptID;
    
    @Column(name="COURSE_CODE")
    int courseCode;
    
    @Column(name="CREATED")
    Date created;
    
    @Column(name="MODIFIED")
    Date modified;
    
    @Column(name="USER_ID_USER")
    int userID;
    
    @Column(name="RATING_STATUS")
    String status;
}
