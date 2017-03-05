package com.wedevol.iclass.core.view.response;

import java.io.Serializable;

/**
 * Course Response
 * 
 * @author charz
 *
 */
public class CourseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private String classMaterialUrl;
	private String exerciseMaterialUrl;
	private String faculty;
	private String university;
	private String status;
	private Float price;
	private String currency;

	protected CourseResponse() {
	}

	public CourseResponse(Long id, String name, String description, String classMaterialUrl, String exerciseMaterialUrl,
			String faculty, String university, String status, Float price, String currency) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.classMaterialUrl = classMaterialUrl;
		this.exerciseMaterialUrl = exerciseMaterialUrl;
		this.faculty = faculty;
		this.university = university;
		this.status = status;
		this.price = price;
		this.currency = currency;
	}

	public CourseResponse(Long id, String name, String description, String classMaterialUrl, String exerciseMaterialUrl,
			String faculty, String university, String status) {
		this(id, name, description, classMaterialUrl, exerciseMaterialUrl, faculty, university, status, null, null);
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

	public String getClassMaterialUrl() {
		return classMaterialUrl;
	}

	public void setClassMaterialUrl(String classMaterialUrl) {
		this.classMaterialUrl = classMaterialUrl;
	}

	public String getExerciseMaterialUrl() {
		return exerciseMaterialUrl;
	}

	public void setExerciseMaterialUrl(String exerciseMaterialUrl) {
		this.exerciseMaterialUrl = exerciseMaterialUrl;
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
		return String.format("CourseResponse[id=%d, name='%s']%n", id, name);
	}

}
