package com.wedevol.iclass.core.view.response;

import java.io.Serializable;

/**
 * Course Full Info Entity
 * 
 * @author charz
 *
 */
public class CourseFullInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private String faculty;
	private String university;
	private String status;
	private Float price;
	private String currency;

	protected CourseFullInfo() {
	}

	public CourseFullInfo(Long id, String name, String description, String faculty, String university, String status,
			Float price, String currency) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.faculty = faculty;
		this.university = university;
		this.status = status;
		this.price = price;
		this.currency = currency;
	}

	public CourseFullInfo(Long id, String name, String description, String faculty, String university, String status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.faculty = faculty;
		this.university = university;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	@Override
	public String toString() {
		return String.format("CourseFullInfo[id=%d, name='%s']%n", id, name);
	}

}
