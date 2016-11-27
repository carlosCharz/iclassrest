package com.wedevol.iclass.core.enums;

/**
 * Place Options Enum
 *
 * @author charz
 */
public enum PlaceOptionsType {
	HOUSE("house"), UNIVERSITY("university");

	private final String description;

	PlaceOptionsType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
