package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

import com.wedevol.iclass.core.entity.constraint.WeekDay;
import com.wedevol.iclass.core.entity.enums.WeekDayType;

/**
 * Class Hour Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "class_hour")
@DynamicInsert
public class ClassHour implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@WeekDay
	@Column(name = "weekday")
	private String weekDay;

	@NotNull
	@Size(min = 1, max = 3, message = "Start time must be between 1 - 3 digits")
	@Digits(integer = 3, fraction = 0, message = "Start time must be just digits")
	@Column
	private Integer startTime;

	@NotNull
	@Size(min = 1, max = 3, message = "End time must be between 1 - 3 digits")
	@Digits(integer = 3, fraction = 0, message = "End time must be just digits")
	@Column
	private Integer endTime;

	protected ClassHour() {
	}

	private ClassHour(ClassHourBuilder builder) {
		this.weekDay = builder.weekDay;
		this.startTime = builder.startTime;
		this.endTime = builder.endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WeekDayType getWeekDay() {
		return WeekDayType.valueOf(weekDay);
	}

	public void setWeekDay(WeekDayType weekDay) {
		this.weekDay = weekDay.getDescription();
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

	/**
	 * Class Hour Builder
	 * 
	 * @author charz
	 *
	 */
	public static class ClassHourBuilder {

		private final String weekDay;
		private final Integer startTime;
		private Integer endTime;

		public ClassHourBuilder(WeekDayType weekDay, Integer startTime, Integer endTime) {
			this.weekDay = weekDay.getDescription();
			this.startTime = startTime;
			this.endTime = endTime;
		}

		public ClassHourBuilder(WeekDayType weekDay, Integer startTime) {
			this.weekDay = weekDay.getDescription();
			this.startTime = startTime;
			this.endTime = startTime + 1;
		}

		public ClassHourBuilder endTime(Integer endTime) {
			this.endTime = endTime;
			return this;
		}

		public ClassHour build() {
			// TODO: analyze if we need to validate here. IllegalStateException.
			return new ClassHour(this);
		}

	}

}
