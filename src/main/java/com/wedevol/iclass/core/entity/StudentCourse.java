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
 * Student_Course Entity
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
	//@CourseStatus
	@Column
	//private CourseStatusType status;
	//TODO: refactor this
	private String status;

	protected StudentCourse() {
	}

	public StudentCourseId getStudentCourseId() {
		return studentCourseId;
	}

	public void setScId(StudentCourseId studentCourseId) {
		this.studentCourseId = studentCourseId;
	}

	public String getStatus() {
		//return status.getDescription();
		return status;
	}

	public void setStatus(String status) {
		//this.status = CourseStatusType.valueOf(status);
		this.status = status;
	}
}
