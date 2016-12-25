package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Student Enrollment Composed Id
 *
 * @author charz
 */
@Embeddable
public class StudentEnrollmentId implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "studentid")
	private Long studentId;

	@NotNull
	@Column(name = "courseid")
	private Long courseId;

	protected StudentEnrollmentId() {
	}

	public StudentEnrollmentId(Long studentId, Long courseId) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

}