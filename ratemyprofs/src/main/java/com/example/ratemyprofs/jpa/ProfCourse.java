package com.example.ratemyprofs.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PROF_COURSE database table.
 * 
 */
@Entity
@Table(name="PROF_COURSE")
@NamedQuery(name="ProfCourse.findAll", query="SELECT p FROM ProfCourse p")
public class ProfCourse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PROF_COURSE", unique=true, nullable=false)
	private int idProfCourse;

	//bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name="COURSE_ID_COURSE", nullable=false)
	private Course course;

	//bi-directional many-to-one association to Prof
	@ManyToOne
	@JoinColumn(name="PROF_ID_PROF", nullable=false)
	private Prof prof;

	public ProfCourse() {
	}

	public int getIdProfCourse() {
		return this.idProfCourse;
	}

	public void setIdProfCourse(int idProfCourse) {
		this.idProfCourse = idProfCourse;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Prof getProf() {
		return this.prof;
	}

	public void setProf(Prof prof) {
		this.prof = prof;
	}

}