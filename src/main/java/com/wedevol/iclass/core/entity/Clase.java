package com.wedevol.iclass.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wedevol.iclass.core.entity.constraint.ClassStatus;
import com.wedevol.iclass.core.entity.constraint.CustomDateDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateSerialize;
import com.wedevol.iclass.core.entity.constraint.CustomDatetimeDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDatetimeSerialize;
import com.wedevol.iclass.core.entity.constraint.WeekDay;

/**
 * Class
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "class")
@DynamicInsert
public class Clase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Digits(integer = 20, fraction = 0, message = "Student id must be just digits")
	@Column(name = "studentid")
	private Long studentId;

	@Digits(integer = 20, fraction = 0, message = "Instructor id must be just digits")
	@Column(name = "instructorid")
	private Long instructorId;

	@Digits(integer = 20, fraction = 0, message = "Course id must be just digits")
	@Column(name = "courseid")
	private Long courseId;

	@WeekDay
	@Column(name = "weekday")
	private String weekDay;

	// TODO: validate future
	@JsonDeserialize(using = CustomDateDeserialize.class)
	@JsonSerialize(using = CustomDateSerialize.class)
	@Column(name = "classdate")
	private Date classDate;

	@Digits(integer = 2, fraction = 0, message = "Start time must be just digits")
	@Column(name = "starttime")
	private Integer startTime;

	@Digits(integer = 2, fraction = 0, message = "End time must be just digits")
	@Column(name = "endtime")
	private Integer endTime;

	@JsonDeserialize(using = CustomDatetimeDeserialize.class)
	@JsonSerialize(using = CustomDatetimeSerialize.class)
	@Column(name = "requestedat")
	private Date requestedAt;

	@ClassStatus
	@Column
	private String status;

	protected Clase() {
	}
	
	private Clase(Long id) {
		this.id = id;
	}

	public static Clase from(Long id) {
		return new Clase(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
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

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public Date getClassDate() {
		return classDate;
	}

	public void setClassDate(Date classDate) {
		this.classDate = classDate;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
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
		return String.format("Clase[id=%d, studentId='%d', instructorId='%d', courseId='%d']%n", id, studentId,
				instructorId, courseId);
	}

}
