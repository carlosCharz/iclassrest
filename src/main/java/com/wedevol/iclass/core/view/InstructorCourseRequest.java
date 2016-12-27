package com.wedevol.iclass.core.view;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateSerialize;

/**
 * Instructor course request
 * 
 * @author charz
 *
 */
public class InstructorCourseRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO: move this class to params
	private Long courseId;
	@JsonDeserialize(using = CustomDateDeserialize.class)
	@JsonSerialize(using = CustomDateSerialize.class)
	private Date classDate;

	protected InstructorCourseRequest() {
	}

	public InstructorCourseRequest(Long courseId, Date classDate) {
		super();
		this.courseId = courseId;
		this.classDate = classDate;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Date getClassDate() {
		return classDate;
	}

	public void setClassDate(Date classDate) {
		this.classDate = classDate;
	}

}
