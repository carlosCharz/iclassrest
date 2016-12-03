package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.wedevol.iclass.core.enums.CourseStatusType;
import com.wedevol.iclass.core.validation.CourseStatus;

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
	@CourseStatus
	@Column
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
		return status;
	}

	public CourseStatusType getStatusType() {
		return CourseStatusType.valueOf(status);
	}

	public void setStatus(CourseStatusType status) {
		this.status = status.getDescription();
	}
}
