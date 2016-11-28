package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.wedevol.iclass.core.enums.CourseStatus;

/**
 * Course Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "student_course")
public class StudentCourse implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StudentCourseId studentCourseId;

	@NotNull
	// TODO: constraint for the enum
	@Column
	private CourseStatus status;

	protected StudentCourse() {
	}

	public StudentCourseId getStudentCourseId() {
		return studentCourseId;
	}

	public void setStudentCourseId(StudentCourseId studentCourseId) {
		this.studentCourseId = studentCourseId;
	}

	public String getStatus() {
		return status.getDescription();
	}

	public void setStatus(String status) {
		this.status = CourseStatus.valueOf(status);
	}
}

@Embeddable
class StudentCourseId implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "studentid")
	private Long studentId;

	@NotNull
	@Column(name = "courseid")
	private Long courseId;

	protected StudentCourseId() {
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
