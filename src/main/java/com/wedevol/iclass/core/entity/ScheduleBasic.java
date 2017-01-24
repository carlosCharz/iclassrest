package com.wedevol.iclass.core.entity;

import java.io.Serializable;

/**
 * Schedule basic class
 * 
 * @author charz
 *
 */
public class ScheduleBasic implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long instructorId;
	private Integer startTime;
	private Integer endTime;

	protected ScheduleBasic() {
	}

	public ScheduleBasic(Long id, Long instructorId, Integer startTime, Integer endTime) {
		super();
		this.id = id;
		this.instructorId = instructorId;
		this.startTime = startTime;
		this.endTime = endTime;
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
	
	@Override
	public String toString() {
		return String.format("ScheduleBasic[id=%d, instructorId='%d', startTime='%d', endTime='%d']%n", id, instructorId, startTime, endTime);
	}

}
