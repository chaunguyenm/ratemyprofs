package com.example.ratemyprofs.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COURSE database table.
 * 
 */
@Entity
@Table(name="COURSE")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_COURSE", unique=true, nullable=false)
	private int idCourse;

	@Column(name="COURSE_CODE", nullable=false, length=10)
	private String courseCode;

	@Column(name="COURSE_NAME", nullable=false, length=128)
	private String courseName;

	@Column(name="COURSE_STATUS", nullable=false, length=1)
	private String courseStatus;

	//bi-directional many-to-one association to Dept
	@ManyToOne
	@JoinColumn(name="ID_DEPT", nullable=false)
	private Dept dept;

	//bi-directional many-to-one association to ProfCourse
	@OneToMany(mappedBy="course")
	private List<ProfCourse> profCourses;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="course")
	private List<Rating> ratings;

	public Course() {
	}

	public int getIdCourse() {
		return this.idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	public String getCourseCode() {
		return this.courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseStatus() {
		return this.courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public List<ProfCourse> getProfCourses() {
		return this.profCourses;
	}

	public void setProfCourses(List<ProfCourse> profCourses) {
		this.profCourses = profCourses;
	}

	public ProfCourse addProfCours(ProfCourse profCours) {
		getProfCourses().add(profCours);
		profCours.setCourse(this);

		return profCours;
	}

	public ProfCourse removeProfCours(ProfCourse profCours) {
		getProfCourses().remove(profCours);
		profCours.setCourse(null);

		return profCours;
	}

	public List<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Rating addRating(Rating rating) {
		getRatings().add(rating);
		rating.setCourse(this);

		return rating;
	}

	public Rating removeRating(Rating rating) {
		getRatings().remove(rating);
		rating.setCourse(null);

		return rating;
	}

}