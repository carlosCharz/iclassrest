package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Instructor Enrollment Composed Id
 *
 * @author charz
 */
@Embeddable
public class InstructorEnrollmentId implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "instructorid")
	private Long instructorId;

	@NotNull
	@Column(name = "courseid")
	private Long courseId;

	protected InstructorEnrollmentId() {
	}

	public InstructorEnrollmentId(Long instructorId, Long courseId) {
		super();
		this.instructorId = instructorId;
		this.courseId = courseId;
	}

	public Long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

}