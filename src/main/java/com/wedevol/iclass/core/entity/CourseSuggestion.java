package com.wedevol.iclass.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wedevol.iclass.core.entity.constraint.CourseSuggestionStatus;
import com.wedevol.iclass.core.entity.constraint.CustomDatetimeDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDatetimeSerialize;

/**
 * Course Suggestion
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "course_suggestion")
@DynamicInsert
public class CourseSuggestion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 2, max = 50, message = "User type must be between 2 - 50 characters")
	@Column(name = "usertype")
	// TODO: analyze enum
	private String userType;

	@Digits(integer = 20, fraction = 0, message = "User id must be just digits")
	@Column(name = "userid")
	private Long userId;

	@Size(min = 2, max = 100, message = "Course name must be between 2 - 100 characters")
	@Column
	private String name;

	@Size(min = 2, max = 200, message = "Course description must be between 2 - 200 characters")
	@Column
	private String description;

	@Digits(integer = 20, fraction = 0, message = "Faculty id must be just digits")
	@Column(name = "facultyid")
	private Long facultyId;

	@Digits(integer = 20, fraction = 0, message = "University id must be just digits")
	@Column(name = "universityid")
	private Long universityId;

	@JsonDeserialize(using = CustomDatetimeDeserialize.class)
	@JsonSerialize(using = CustomDatetimeSerialize.class)
	@Column(name = "requestedat")
	private Date requestedAt;

	@CourseSuggestionStatus
	@Column
	private String status;

	public static CourseSuggestion from(Long id) {
		return new CourseSuggestion(id);
	}

	private CourseSuggestion(Long id) {
		this.id = id;
	}

	protected CourseSuggestion() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Long universityId) {
		this.universityId = universityId;
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
		return String.format("CourseSuggestion[id=%d, name='%s']%n", id, name);
	}
}
