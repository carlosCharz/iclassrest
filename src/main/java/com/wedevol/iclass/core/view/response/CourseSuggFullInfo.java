package com.wedevol.iclass.core.view.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDatetimeDeserialize;

/**
 * Course Suggestion Full Information
 * 
 * @author charz
 *
 */
public class CourseSuggFullInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String userType;
	private Long userId;
	private String userName;
	private String userEmail;
	private String courseName;
	private String description;
	private Long facultyId;
	private String facultyName;
	private Long universityId;
	private String universityName;
	@JsonDeserialize(using = CustomDatetimeDeserialize.class)
	private Date requestedAt;
	private String status;

	public CourseSuggFullInfo(Long id, String userType, Long userId, String userName, String userEmail,
			String courseName, String description, Long facultyId, String facultyName, Long universityId,
			String universityName, Date requestedAt, String status) {
		super();
		this.id = id;
		this.userType = userType;
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.courseName = courseName;
		this.description = description;
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.universityId = universityId;
		this.universityName = universityName;
		this.requestedAt = requestedAt;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public Long getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Long universityId) {
		this.universityId = universityId;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public Date getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(Date requestedAt) {
		this.requestedAt = requestedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("CourseSuggInfo[id=%d, courseName='%s']%n", id, courseName);
	}
}
