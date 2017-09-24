package com.wedevol.iclass.core.entity.enums;

/**
 * Course Suggestion Status Enum (for student or instructor)
 *
 * @author Charz++
 */
public enum CourseSuggestionStatusType {
	SUGGESTED("suggested"), 
	ACCEPTED("accepted"), 
	REJECTED("rejected");

	private final String description;

	CourseSuggestionStatusType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
