package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

/**
 * Course Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "course")
@DynamicInsert
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 2, max = 100, message = "Course name must be between 2 - 100 characters")
	@Column
	private String name;

	@Size(min = 2, max = 200, message = "Course description must be between 2 - 200 characters")
	@Column
	private String description;

	@Digits(integer = 20, fraction = 0, message = "Faculty id must be just digits")
	@Column(name = "facultyid")
	private Long facultyId;

	@Digits(integer = 20, fraction = 0, message = "University id must be just digits")
	@Column(name = "universityid")
	private Long universityId;
	
	@Size(min = 2, max = 100, message = "Class material url must be between 2 - 100 characters")
	@Column(name = "classmaterialurl")
	private String classMaterialUrl;
	
	@Size(min = 2, max = 100, message = "Exercise material url must be between 2 - 100 characters")
	@Column(name = "exercisematerialurl")
	private String exerciseMaterialUrl;

	public static Course from(Long id) {
		return new Course(id);
	}

	private Course(Long id) {
		this.id = id;
	}

	protected Course() {
	}

	private Course(CourseBuilder builder) {
		this.name = builder.name;
		this.description = builder.description;
		this.facultyId = builder.facultyId;
		this.universityId = builder.universityId;
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

	public Long getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}

	public Long getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Long universityId) {
		this.universityId = universityId;
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

	/**
	 * Course Builder
	 * 
	 * @author charz
	 *
	 */
	public static class CourseBuilder {

		private String name;
		private String description;
		private Long facultyId;
		private Long universityId;

		public CourseBuilder(String name) {
			this.name = name;
		}

		public CourseBuilder description(String description) {
			this.description = description;
			return this;
		}

		public CourseBuilder faculty(Long facultyId) {
			this.facultyId = facultyId;
			return this;
		}

		public CourseBuilder university(Long universityId) {
			this.universityId = universityId;
			return this;
		}

		public Course build() {
			return new Course(this);
		}

	}

	@Override
	public String toString() {
		return String.format("Course[id=%d, name='%s']%n", id, name);
	}

}
