package com.example.ratemyprofs.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@Table(name="USER")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=320)
	private String email;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USER", unique=true, nullable=false)
	private int idUser;

	@Column(nullable=false, length=45)
	private String password;

	@Column(name="USER_FIRST_NAME", length=45)
	private String userFirstName;

	@Column(name="USER_LAST_NAME", length=45)
	private String userLastName;

	@Column(name="USER_STATUS", nullable=false, length=1)
	private String userStatus;

	@Column(nullable=false, length=45)
	private String username;

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserFirstName() {
		return this.userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return this.userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}