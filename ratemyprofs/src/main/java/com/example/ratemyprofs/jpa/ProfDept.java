package com.example.ratemyprofs.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PROF_DEPT database table.
 * 
 */
@Entity
@Table(name="PROF_DEPT")
@NamedQuery(name="ProfDept.findAll", query="SELECT p FROM ProfDept p")
public class ProfDept implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PROF_DEPT", unique=true, nullable=false)
	private int idProfDept;

	//bi-directional many-to-one association to Dept
	@ManyToOne
	@JoinColumn(name="DEPT_ID_DEPT", nullable=false)
	private Dept dept;

	//bi-directional many-to-one association to Prof
	@ManyToOne
	@JoinColumn(name="PROF_ID_PROF", nullable=false)
	private Prof prof;

	public ProfDept() {
	}

	public int getIdProfDept() {
		return this.idProfDept;
	}

	public void setIdProfDept(int idProfDept) {
		this.idProfDept = idProfDept;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Prof getProf() {
		return this.prof;
	}

	public void setProf(Prof prof) {
		this.prof = prof;
	}

}