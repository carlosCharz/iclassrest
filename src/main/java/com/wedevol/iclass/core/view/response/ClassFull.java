package com.wedevol.iclass.core.view.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDatetimeDeserialize;

/**
 * Class Full Information
 * 
 * @author charz
 *
 */
public class ClassFull implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long studentId;
	private String studentName;
	private Long instructorId;
	private String instructorName;
	private Long courseId;
	private String courseName;
	private String weekDay;
	@JsonDeserialize(using = CustomDateDeserialize.class)
	private Date classDate;
	private Integer startTime;
	private Integer endTime;
	@JsonDeserialize(using = CustomDatetimeDeserialize.class)
	private Date requestedAt;
	private Float ratingToInstructor;
	private Float ratingToStudent;
	private String status;
	
	public ClassFull() {
	}
	
	public ClassFull(Long id, Long studentId, String studentName, Long instructorId, String instructorName,
			Long courseId, String courseName, String weekDay, Date classDate, Integer startTime, Integer endTime,
			Date requestedAt, Float ratingToInstructor, Float ratingToStudent, String status) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.studentName = studentName;
		this.instructorId = instructorId;
		this.instructorName = instructorName;
		this.courseId = courseId;
		this.courseName = courseName;
		this.weekDay = weekDay;
		this.classDate = classDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.requestedAt = requestedAt;
		this.ratingToInstructor = ratingToInstructor;
		this.ratingToStudent = ratingToStudent;
		this.status = status;
	}

	private ClassFull(Long id) {
		this.id = id;
	}

	public static ClassFull from(Long id) {
		return new ClassFull(id);
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

	public Float getRatingToInstructor() {
		return ratingToInstructor;
	}

	public void setRatingToInstructor(Float ratingToInstructor) {
		this.ratingToInstructor = ratingToInstructor;
	}

	public Float getRatingToStudent() {
		return ratingToStudent;
	}

	public void setRatingToStudent(Float ratingToStudent) {
		this.ratingToStudent = ratingToStudent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return String.format("ClassFullInfo[id=%d, studentId='%d', instructorId='%d', courseId='%d']%n", id, studentId,
				instructorId, courseId);
	}

}
