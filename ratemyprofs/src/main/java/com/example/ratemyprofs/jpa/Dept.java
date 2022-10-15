package com.example.ratemyprofs.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DEPT database table.
 * 
 */
@Entity
@Table(name="DEPT")
@NamedQuery(name="Dept.findAll", query="SELECT d FROM Dept d")
public class Dept implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DEPT", unique=true, nullable=false)
	private int idDept;

	@Column(name="DEPT_NAME", nullable=false, length=128)
	private String deptName;

	@Column(name="DEPT_STATUS", nullable=false, length=1)
	private String deptStatus;

	//bi-directional many-to-one association to Course
	@OneToMany(mappedBy="dept")
	private List<Course> courses;

	//bi-directional many-to-one association to Inst
	@ManyToOne
	@JoinColumn(name="ID_INST", nullable=false)
	private Inst inst;

	//bi-directional many-to-one association to ProfDept
	@OneToMany(mappedBy="dept")
	private List<ProfDept> profDepts;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="dept")
	private List<Rating> ratings;

	public Dept() {
	}

	public int getIdDept() {
		return this.idDept;
	}

	public void setIdDept(int idDept) {
		this.idDept = idDept;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptStatus() {
		return this.deptStatus;
	}

	public void setDeptStatus(String deptStatus) {
		this.deptStatus = deptStatus;
	}

	public List<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course addCours(Course cours) {
		getCourses().add(cours);
		cours.setDept(this);

		return cours;
	}

	public Course removeCours(Course cours) {
		getCourses().remove(cours);
		cours.setDept(null);

		return cours;
	}

	public Inst getInst() {
		return this.inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

	public List<ProfDept> getProfDepts() {
		return this.profDepts;
	}

	public void setProfDepts(List<ProfDept> profDepts) {
		this.profDepts = profDepts;
	}

	public ProfDept addProfDept(ProfDept profDept) {
		getProfDepts().add(profDept);
		profDept.setDept(this);

		return profDept;
	}

	public ProfDept removeProfDept(ProfDept profDept) {
		getProfDepts().remove(profDept);
		profDept.setDept(null);

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
		rating.setDept(this);

		return rating;
	}

	public Rating removeRating(Rating rating) {
		getRatings().remove(rating);
		rating.setDept(null);

		return rating;
	}

}