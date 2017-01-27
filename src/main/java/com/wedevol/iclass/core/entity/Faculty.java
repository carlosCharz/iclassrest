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
 * Faculty Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "faculty")
@DynamicInsert
public class Faculty implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Digits(integer = 20, fraction = 0, message = "University id must be just digits")
	@Column(name = "universityid")
	private Long universityId;

	@Size(min = 2, max = 100, message = "Faculty name must be between 2 - 100 characters")
	@Column
	private String name;

	@Size(min = 2, max = 50, message = "Faculty short name must be between 2 - 50 characters")
	@Column(name = "shortname")
	private String shortName;

	protected Faculty() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Long universityId) {
		this.universityId = universityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return String.format("Faculty[id=%d, name='%s', shortName='%s']%n", id, name, shortName);
	}

}
