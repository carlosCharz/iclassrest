package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

/**
 * University Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "university")
@DynamicInsert
public class University implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 2, max = 100, message = "University name must be between 2 - 100 characters")
	@Column
	private String name;

	@Size(min = 2, max = 50, message = "University short name must be between 2 - 50 characters")
	@Column(name = "shortname")
	private String shortName;

	protected University() {
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return String.format("University[id=%d, name='%s', shortName='%s']%n", id, name, shortName);
	}

}
