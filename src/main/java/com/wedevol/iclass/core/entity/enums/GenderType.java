package com.wedevol.iclass.core.entity.enums;

/**
 * Gender Type Enum
 *
 * @author charz
 */
public enum GenderType {
	// TODO: add enum in database
	MALE("M"), 
	FEMALE("F");

	private final String description;

	GenderType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
