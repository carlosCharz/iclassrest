package com.wedevol.iclass.core.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@NotNull
	@Size(min = 2, max = 100, message = "Course name must be between 2 - 100 characters")
	@Column
	private String name;

	@Size(min = 2, max = 200, message = "Course description must be between 2 - 200 characters")
	@Column
	private String description;

	@Size(min = 2, max = 100, message = "University name must be between 2 - 100 characters")
	@Column(nullable = true)
	private String university;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Topic> topics;

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

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
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

		public Course build() {
			// TODO: analyze if we need to validate here. IllegalStateException.
			return new Course(this);
		}

	}

	@Override
	public String toString() {
		String result = String.format("Course[id=%d, name='%s']%n", id, name);
		if (topics != null) {
			for (Topic topic : topics) {
				result += String.format("Topic[id=%d, name='%s']%n", topic.getId(), topic.getName());
			}
		}
		return result;
	}

}
