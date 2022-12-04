package com.example.ratemyprofs.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the RATING database table.
 * 
 */
@Entity
@Table(name="RATING")
@NamedQuery(name="Rating.findAll", query="SELECT r FROM Rating r")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_RATING", unique=true, nullable=false)
	private int idRating;

	@Column(name="COURSE_CODE", length=10)
	private String courseCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date created;

	@Column(name="DIFFICULTY_LEVEL", nullable=false)
	private int difficultyLevel;

	@Column(name="FOR_CREDIT")
	private Boolean forCredit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date modified;

	@Column(name="OVERALL_SCORE", nullable=false)
	private int overallScore;

	@Column(name="RATING_STATUS", nullable=false, length=1)
	private String ratingStatus;

	@Column(name="RECEIVED_GRADE", length=2)
	private String receivedGrade;

	@Column(name="REQUIRE_ATTENDANCE")
	private Boolean requireAttendance;

	@Column(name="REQUIRE_TEXTBOOK")
	private Boolean requireTextbook;

	@Column(nullable=false, length=350)
	private String review;

	@Column(name="USER_ID_USER", nullable=true)
	private Integer userIdUser;

	@Column(name="WILL_RETAKE", nullable=false)
	private Boolean willRetake;

	//bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name="COURSE_ID_COURSE")
	private Course course;

	//bi-directional many-to-one association to Dept
	@ManyToOne
	@JoinColumn(name="DEPT_ID_DEPT")
	private Dept dept;

	//bi-directional many-to-one association to Prof
	@ManyToOne
	@JoinColumn(name="PROF_ID_PROF", nullable=false)
	private Prof prof;

	public Rating() {
	}

	public int getIdRating() {
		return this.idRating;
	}

	public void setIdRating(int idRating) {
		this.idRating = idRating;
	}

	public String getCourseCode() {
		return this.courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getDifficultyLevel() {
		return this.difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Boolean getForCredit() {
		return this.forCredit;
	}

	public void setForCredit(Boolean forCredit) {
		this.forCredit = forCredit;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public int getOverallScore() {
		return this.overallScore;
	}

	public void setOverallScore(int overallScore) {
		this.overallScore = overallScore;
	}

	public String getRatingStatus() {
		return this.ratingStatus;
	}

	public void setRatingStatus(String ratingStatus) {
		this.ratingStatus = ratingStatus;
	}

	public String getReceivedGrade() {
		return this.receivedGrade;
	}

	public void setReceivedGrade(String receivedGrade) {
		this.receivedGrade = receivedGrade;
	}

	public Boolean getRequireAttendance() {
		return this.requireAttendance;
	}

	public void setRequireAttendance(Boolean requireAttendance) {
		this.requireAttendance = requireAttendance;
	}

	public Boolean getRequireTextbook() {
		return this.requireTextbook;
	}

	public void setRequireTextbook(Boolean requireTextbook) {
		this.requireTextbook = requireTextbook;
	}

	public String getReview() {
		return this.review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getUserIdUser() {
		return this.userIdUser;
	}

	public void setUserIdUser(Integer userIdUser) {
		this.userIdUser = userIdUser;
	}

	public Boolean getWillRetake() {
		return this.willRetake;
	}

	public void setWillRetake(Boolean willRetake) {
		this.willRetake = willRetake;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
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