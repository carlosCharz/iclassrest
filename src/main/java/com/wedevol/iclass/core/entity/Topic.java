package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Topic Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "topic")
@DynamicInsert
public class Topic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "courseid")
	@JsonBackReference
	private Course course;

	@NotNull
	@Size(min = 2, max = 100, message = "Topic name must be between 2 - 100 characters")
	@Column
	private String name;

	protected Topic() {
	}

	private Topic(TopicBuilder builder) {
		this.course = builder.course;
		this.name = builder.name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	@JsonProperty
	public Long getCourseId() {
		return course == null ? null : course.getId();
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Topic Builder
	 * 
	 * @author charz
	 *
	 */
	public static class TopicBuilder {

		private Course course;
		private String name;

		public TopicBuilder(Course course, String name) {
			this.course = course;
			this.name = name;
		}

		public TopicBuilder course(Course course) {
			this.course = course;
			return this;
		}

		public TopicBuilder name(String name) {
			this.name = name;
			return this;
		}

		public Topic build() {
			// TODO: analyze if we need to validate here. IllegalStateException.
			return new Topic(this);
		}

	}

}
