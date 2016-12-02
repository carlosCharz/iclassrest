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

/**
 * Topic Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "topic")
@DynamicInsert
// It excludes null properties
public class Topic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 100, message = "Course name must be between 2 - 100 characters")
	@Digits(integer = 11, fraction = 0, message = "Topic id must be just digits")
	@Column
	private Long courseId;

	@NotNull
	@Size(min = 2, max = 100, message = "Course name must be between 2 - 100 characters")
	@Column
	private String name;

	protected Topic() {
	}

	private Topic(TopicBuilder builder) {
		this.name = builder.name;
		this.description = builder.description;
		this.university = builder.university;
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

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
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
		private String university;

		public CourseBuilder(String name) {
			this.name = name;
		}

		public CourseBuilder description(String description) {
			this.description = description;
			return this;
		}

		public CourseBuilder university(String university) {
			this.university = university;
			return this;
		}

		public Topic build() {
			// TODO: analyze if we need to validate here. IllegalStateException.
			return new Topic(this);
		}

	}

}
