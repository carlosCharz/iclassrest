package com.wedevol.iclass.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wedevol.iclass.core.entity.constraint.ClassStatus;
import com.wedevol.iclass.core.entity.constraint.CustomDateDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateSerialize;
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

	@NotNull
	@Digits(integer = 20, fraction = 0, message = "Student id must be just digits")
	@Column(name = "studentid")
	private Long studentId;

	@NotNull
	@Digits(integer = 20, fraction = 0, message = "Instructor id must be just digits")
	@Column(name = "instructorid")
	private Long instructorId;

	@NotNull
	@Digits(integer = 20, fraction = 0, message = "Course id must be just digits")
	@Column(name = "courseid")
	private Long courseId;

	@NotNull
	@WeekDay
	@Column(name = "weekday")
	private String weekDay;

	@Future
	@JsonDeserialize(using = CustomDateDeserialize.class)
	@JsonSerialize(using = CustomDateSerialize.class)
	@Column(name = "classdate")
	private Date classDate;

	@NotNull
	@Digits(integer = 2, fraction = 0, message = "Start time must be just digits")
	@Column(name = "starttime")
	private Integer startTime;

	@NotNull
	@Digits(integer = 2, fraction = 0, message = "End time must be just digits")
	@Column(name = "endtime")
	private Integer endTime;

	// TODO: Analize if past or future
	@JsonDeserialize(using = CustomDateDeserialize.class)
	@JsonSerialize(using = CustomDateSerialize.class)
	@Column(name = "requestedat", nullable = true)
	private Date requestedAt;

	@NotNull
	@ClassStatus
	@Column
	private String status;

	protected Clase() {
	}

	private Clase(ClassBuilder builder) {
		this.studentId = builder.studentId;
		this.instructorId = builder.instructorId;
		this.courseId = builder.courseId;
		this.weekDay = builder.weekDay;
		this.classDate = builder.classDate;
		this.startTime = builder.startTime;
		this.endTime = builder.endTime;
		this.status = builder.status;
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

	/**
	 * Class Builder
	 * 
	 * @author charz
	 *
	 */
	public static class ClassBuilder {

		private final Long studentId;
		private final Long instructorId;
		private final Long courseId;
		private final String weekDay;
		private final Date classDate;
		private final Integer startTime;
		private final Integer endTime;
		private final String status;

		public ClassBuilder(Long studentId, Long instructorId, Long courseId, String weekDay, Date classDate,
				Integer startTime, Integer endTime, String status) {
			this.studentId = studentId;
			this.instructorId = instructorId;
			this.courseId = courseId;
			this.weekDay = weekDay;
			this.classDate = classDate;
			this.startTime = startTime;
			this.endTime = endTime;
			this.status = status;
		}

		public Clase build() {
			return new Clase(this);
		}

	}

}
