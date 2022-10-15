package com.example.ratemyprofs.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PROF database table.
 * 
 */
@Entity
@Table(name="PROF")
@NamedQuery(name="Prof.findAll", query="SELECT p FROM Prof p")
public class Prof implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PROF", unique=true, nullable=false)
	private int idProf;

	@Column(name="PROF_FIRST_NAME", nullable=false, length=45)
	private String profFirstName;

	@Column(name="PROF_LAST_NAME", nullable=false, length=45)
	private String profLastName;

	@Column(name="PROF_STATUS", nullable=false, length=45)
	private String profStatus;

	//bi-directional many-to-one association to ProfCourse
	@OneToMany(mappedBy="prof")
	private List<ProfCourse> profCourses;

	//bi-directional many-to-one association to ProfDept
	@OneToMany(mappedBy="prof")
	private List<ProfDept> profDepts;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="prof")
	private List<Rating> ratings;

	public Prof() {
	}

	public int getIdProf() {
		return this.idProf;
	}

	public void setIdProf(int idProf) {
		this.idProf = idProf;
	}

	public String getProfFirstName() {
		return this.profFirstName;
	}

	public void setProfFirstName(String profFirstName) {
		this.profFirstName = profFirstName;
	}

	public String getProfLastName() {
		return this.profLastName;
	}

	public void setProfLastName(String profLastName) {
		this.profLastName = profLastName;
	}

	public String getProfStatus() {
		return this.profStatus;
	}

	public void setProfStatus(String profStatus) {
		this.profStatus = profStatus;
	}

	public List<ProfCourse> getProfCourses() {
		return this.profCourses;
	}

	public void setProfCourses(List<ProfCourse> profCourses) {
		this.profCourses = profCourses;
	}

	public ProfCourse addProfCours(ProfCourse profCours) {
		getProfCourses().add(profCours);
		profCours.setProf(this);

		return profCours;
	}

	public ProfCourse removeProfCours(ProfCourse profCours) {
		getProfCourses().remove(profCours);
		profCours.setProf(null);

		return profCours;
	}

	public List<ProfDept> getProfDepts() {
		return this.profDepts;
	}

	public void setProfDepts(List<ProfDept> profDepts) {
		this.profDepts = profDepts;
	}

	public ProfDept addProfDept(ProfDept profDept) {
		getProfDepts().add(profDept);
		profDept.setProf(this);

		return profDept;
	}

	public ProfDept removeProfDept(ProfDept profDept) {
		getProfDepts().remove(profDept);
		profDept.setProf(null);

		return profDept;
	}

	public List<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Rating addRating(Rating rating) {
		getRatings().add(rating);
		rating.setProf(this);

		return rating;
	}

	public Rating removeRating(Rating rating) {
		getRatings().remove(rating);
		rating.setProf(null);

		return rating;
	}
	
	public String getProfName() {
	    return this.getProfFirstName().concat(" ").concat(this.getProfLastName());
	}

}