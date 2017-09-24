package com.wedevol.iclass.core.entity.enums;

/**
 * User Type Enum
 *
 * @author charz
 */
public enum UserType {
	STUDENT("student"), 
	INSTRUCTOR("instructor"), 
	ADMIN("admin");

	private final String description;

	UserType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
