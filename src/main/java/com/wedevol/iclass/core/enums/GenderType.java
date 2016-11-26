package com.wedevol.iclass.core.enums;

/**
 * Gender Type Enum
 *
 * @author charz
 */
public enum GenderType {
	MALE("M"), FEMALE("F");

	private final String description;

	GenderType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
