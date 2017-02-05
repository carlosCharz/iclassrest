package com.wedevol.iclass.core.entity.enums;

/**
 * Class Status Enum
 *
 * @author charz
 */
public enum ClassStatusType {
	REQUESTED("requested"), CONFIRMED("confirmed"), DONE("done"), REJECTED("rejected"), IGNORED("ignored"),
	CANCELLED("cancelled");
	private final String description;

	ClassStatusType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
