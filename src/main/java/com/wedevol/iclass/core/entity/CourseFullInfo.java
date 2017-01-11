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
	private String university;
	private String status;
	private Integer price;
	private String currency;

	protected CourseFullInfo() {
	}

	public CourseFullInfo(Long id, String name, String description, String university, String status, Integer price,
			String currency) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.university = university;
		this.status = status;
		this.price = price;
		this.currency = currency;
	}

}
