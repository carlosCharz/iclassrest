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
	
	@Override
	public String toString() {
		return String.format("InstructorEnrollmentId[studentId=%d, courseId=%d]%n", instructorId, courseId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstructorEnrollmentId other = (InstructorEnrollmentId) obj;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		return true;
	}

}