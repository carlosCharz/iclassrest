package com.wedevol.iclass.core.entity.enums;

/**
 * Material Type Enum
 *
 * @author charz
 */
public enum MaterialType {
	CLASS("class"), 
	EXERCISE("exercise");
	
	private final String description;

	MaterialType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
