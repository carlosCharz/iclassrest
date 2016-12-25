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
import com.wedevol.iclass.core.entity.constraint.CustomDateDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateSerialize;
import com.wedevol.iclass.core.entity.constraint.WeekDay;

/**
 * Instructor Schedule
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "instructor_schedule")
@DynamicInsert
public class InstructorSchedule implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Digits(integer = 20, fraction = 0, message = "Instructor id must be just digits")
	@Column(name = "instructorid")
	private Long instructorId;

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

	@Column(nullable = true)
	private Boolean available;

	protected InstructorSchedule() {
	}

	private InstructorSchedule(InstructorScheduleBuilder builder) {
		this.instructorId = builder.instructorId;
		this.weekDay = builder.weekDay;
		this.classDate = builder.classDate;
		this.startTime = builder.startTime;
		this.endTime = builder.endTime;
		this.available = builder.available;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
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

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	/**
	 * Instructor Schedule Builder
	 * 
	 * @author charz
	 *
	 */
	public static class InstructorScheduleBuilder {

		private final Long instructorId;
		private final String weekDay;
		private final Date classDate;
		private final Integer startTime;
		private final Integer endTime;
		private Boolean available;

		public InstructorScheduleBuilder(Long instructorId, String weekDay, Date classDate, Integer startTime,
				Integer endTime) {
			this.instructorId = instructorId;
			this.weekDay = weekDay;
			this.classDate = classDate;
			this.startTime = startTime;
			this.endTime = endTime;
		}

		public InstructorScheduleBuilder available(Boolean available) {
			this.available = available;
			return this;
		}

		public InstructorSchedule build() {
			// TODO: analyze if we need to validate here. IllegalStateException.
			return new InstructorSchedule(this);
		}

	}

}
