package com.wedevol.iclass.core.view.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateSerialize;

/**
 * Class response
 * 
 * @author charz
 *
 */
public class ClassResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long classId;
	private Integer startTime;
	private Integer endTime;
	private String weekDay;
	@JsonDeserialize(using = CustomDateDeserialize.class)
	@JsonSerialize(using = CustomDateSerialize.class)
	private Date classDate;
	private String classStatus;
	private Long courseId;
	private String courseName;
	private String userType;
	private Long userId;
	private String firstName;
	private String lastName;
	private String phone;
	private Float price;
	private String currency;
	private Float ratingToInstructor;
	private Float ratingToStudent;

	protected ClassResponse() {
	}

	public ClassResponse(Long classId, Integer startTime, Integer endTime, String weekDay, Date classDate,
			String classStatus, Long courseId, String courseName, String userType, Long userId, String firstName,
			String lastName, String phone, Float price, String currency, Float ratingToInstructor, Float ratingToStudent) {
		super();
		this.classId = classId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.weekDay = weekDay;
		this.classDate = classDate;
		this.classStatus = classStatus;
		this.courseId = courseId;
		this.courseName = courseName;
		this.userType = userType;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.price = price;
		this.currency = currency;
		this.ratingToInstructor = ratingToInstructor;
		this.ratingToStudent = ratingToStudent;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
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

	public String getClassStatus() {
		return classStatus;
	}

	public void setClassStatus(String classStatus) {
		this.classStatus = classStatus;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	@Override
	public String toString() {
		return String.format("ClassResponse[classId=%d, startTime='%d', endTime='%d', weekDay='%s']%n", classId,
				startTime, endTime, weekDay);
	}

}
