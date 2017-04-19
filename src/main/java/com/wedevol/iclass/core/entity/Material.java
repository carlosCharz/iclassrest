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
 * Material Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "material")
@DynamicInsert
public class Material implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Digits(integer = 20, fraction = 0, message = "Course id must be just digits")
	@Column(name = "courseid")
	private Long courseId;

	@Size(min = 2, max = 100, message = "Material name must be between 2 - 100 characters")
	@Column
	private String name;

	@Size(min = 2, max = 100, message = "Material url must be between 2 - 100 characters")
	@Column(name = "url")
	private String url;

	public static Material from(Long id) {
		return new Material(id);
	}

	private Material(Long id) {
		this.id = id;
	}

	public Material() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return String.format("Material[id=%d, courseId='%d', name='%s', url='%s']%n", id, courseId, name, url);
	}

}
