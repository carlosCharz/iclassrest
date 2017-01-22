package com.wedevol.iclass.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wedevol.iclass.core.entity.constraint.CourseSuggestionStatus;
import com.wedevol.iclass.core.entity.constraint.CustomDateDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateSerialize;

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

	@NotNull
	// TODO: remove not nulls
	@Size(min = 2, max = 100, message = "User type must be between 2 - 50 characters")
	@Column(name = "usertype")
	// TODO: analyze enum
	private String userType;

	@NotNull
	@Digits(integer = 20, fraction = 0, message = "User id must be just digits")
	@Column(name = "userid")
	private Long userId;

	@NotNull
	@Size(min = 2, max = 100, message = "Course name must be between 2 - 100 characters")
	@Column
	private String name;

	@Size(min = 2, max = 200, message = "Course description must be between 2 - 200 characters")
	@Column
	private String description;

	@Size(min = 2, max = 100, message = "Faculty name must be between 2 - 100 characters")
	@Column(nullable = true)
	private String faculty;

	@Size(min = 2, max = 100, message = "University name must be between 2 - 100 characters")
	@Column(nullable = true)
	private String university;

	// TODO: Analize if past or future
	@JsonDeserialize(using = CustomDateDeserialize.class)
	@JsonSerialize(using = CustomDateSerialize.class)
	@Column(name = "requestedat", nullable = true)
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

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	@Override
	public String toString() {
		return String.format("Course[id=%d, name='%s']%n", id, name);
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
}
