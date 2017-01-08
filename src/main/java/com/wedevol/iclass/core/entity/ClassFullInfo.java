package com.wedevol.iclass.core.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateSerialize;

/**
 * Class full information
 * 
 * @author charz
 *
 */
public class ClassFullInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long classId;
	private Integer startTime;
	private Integer endTime;
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
	private Integer price;
	private String currency;

	protected ClassFullInfo() {
	}

	public ClassFullInfo(Long classId, Integer startTime, Integer endTime, Date classDate, String classStatus,
			Long courseId, String courseName, String userType, Long userId, String firstName, String lastName,
			String phone, Integer price, String currency) {
		super();
		this.classId = classId;
		this.startTime = startTime;
		this.endTime = endTime;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
