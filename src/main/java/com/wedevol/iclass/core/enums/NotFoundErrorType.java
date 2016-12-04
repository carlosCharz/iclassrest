package com.wedevol.iclass.core.enums;

/**
 * Resource Not Found Error Type Enum (4xx family)
 *
 * @author charz
 */
public enum NotFoundErrorType {
	STUDENT_NOT_FOUND(100, "Student not found"), STUDENT_COURSE_NOT_FOUND(101,
			"Student enrollment not found"), COURSE_NOT_FOUND(102, "Course not found"), TOPIC_NOT_FOUND(103,
					"Topic not found"), INSTRUCTOR_NOT_FOUND(104, "Instructor not found");

	private final int code;
	private final String message;

	NotFoundErrorType(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
