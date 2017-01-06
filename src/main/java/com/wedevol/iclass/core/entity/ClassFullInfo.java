package com.wedevol.iclass.core.entity;

import java.io.Serializable;
import java.util.Date;

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
	private Date classDate;
	private Long courseId;
	private String courseName;
	private Long studentId;
	private String studentFirstName;
	private String studentLastName;
	private String studentPhone;
	private Integer price;
	private String currency;

	protected ClassFullInfo() {
	}

	public ClassFullInfo(Long classId, Long courseId, Long studentId) {
		super();
		this.classId = classId;
		this.courseId = courseId;
		this.studentId = studentId;
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

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
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
