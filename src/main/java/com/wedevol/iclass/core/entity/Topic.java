package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

	@ManyToOne
    @JoinColumn(name = "courseid")
	private Course course;

	@NotNull
	@Size(min = 2, max = 100, message = "Topic name must be between 2 - 100 characters")
	@Column
	private String name;

	protected Topic() {
	}

	private Topic(TopicBuilder builder) {
		this.name = builder.name;
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

	/**
	 * Topic Builder
	 * 
	 * @author charz
	 *
	 */
	public static class TopicBuilder {

		private Long courseId;
		private String name;

		public TopicBuilder(Long courseId, String name) {
			this.courseId = courseId;
			this.name = name;
		}

		public TopicBuilder courseId(Long courseId) {
			this.courseId = courseId;
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
