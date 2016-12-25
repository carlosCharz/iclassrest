package com.wedevol.iclass.core.entity.enums;

/**
 * Class Status Enum
 *
 * @author charz
 */
public enum ClassStatusType {
	REQUESTED("requested"), CONFIRMED("confirmed"), REJECTED("rejected"), IGNORED("ignored");
	private final String description;

	ClassStatusType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
