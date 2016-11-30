package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Student Enrollment Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "student_enrollment")
public class StudentEnrollment implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StudentEnrollmentId studentEnrollmentId;

	@NotNull
	// @CourseStatus
	@Column
	// private CourseStatusType status;
	// TODO: refactor this
	private String status;

	protected StudentEnrollment() {
	}

	public StudentEnrollmentId getStudentEnrollmentId() {
		return studentEnrollmentId;
	}

	public void setStudentEnrollmentId(StudentEnrollmentId studentEnrollmentId) {
		this.studentEnrollmentId = studentEnrollmentId;
	}

	public String getStatus() {
		// return status.getDescription();
		return status;
	}

	public void setStatus(String status) {
		// this.status = CourseStatusType.valueOf(status);
		this.status = status;
	}
}
