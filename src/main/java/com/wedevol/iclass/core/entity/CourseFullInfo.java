package com.wedevol.iclass.core.entity;

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
	private Integer price;
	private String currency;

	protected CourseFullInfo() {
	}

	public CourseFullInfo(Long id, String name, String description, String faculty, String university, String status,
			Integer price, String currency) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.faculty = faculty;
		this.university = university;
		this.status = status;
		this.price = price;
		this.currency = currency;
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
