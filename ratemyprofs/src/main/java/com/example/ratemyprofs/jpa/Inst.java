package com.example.ratemyprofs.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the INST database table.
 * 
 */
@Entity
@Table(name="INST")
@NamedQuery(name="Inst.findAll", query="SELECT i FROM Inst i")
public class Inst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_INST", unique=true, nullable=false)
	private int idInst;

	@Column(name="INST_NAME", nullable=false, length=128)
	private String instName;

	@Column(name="INST_STATUS", nullable=false, length=1)
	private String instStatus;

	//bi-directional many-to-one association to Dept
	@OneToMany(mappedBy="inst")
	private List<Dept> depts;

	public Inst() {
	}

	public int getIdInst() {
		return this.idInst;
	}

	public void setIdInst(int idInst) {
		this.idInst = idInst;
	}

	public String getInstName() {
		return this.instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getInstStatus() {
		return this.instStatus;
	}

	public void setInstStatus(String instStatus) {
		this.instStatus = instStatus;
	}

	public List<Dept> getDepts() {
		return this.depts;
	}

	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}

	public Dept addDept(Dept dept) {
		getDepts().add(dept);
		dept.setInst(this);

		return dept;
	}

	public Dept removeDept(Dept dept) {
		getDepts().remove(dept);
		dept.setInst(null);

		return dept;
	}

}