package com.wedevol.iclass.core.exception.enums;

/**
 * Resource Not Found Error Type Enum (404 family)
 *
 * @author charz
 */
public enum NotFoundErrorType {
	STUDENT_NOT_FOUND(404, "Student not found"), STUDENT_COURSE_NOT_FOUND(404, "Student enrollment not found"),
	COURSE_NOT_FOUND(404, "Course not found"), TOPIC_NOT_FOUND(404, "Topic not found"),
	INSTRUCTOR_NOT_FOUND(404, "Instructor not found"),
	INSTRUCTOR_COURSE_NOT_FOUND(404, "Instructor enrollment not found"),
	INSTRUCTOR_SCHEDULE_NOT_FOUND(404, "Instructor schedule not found"), CLASS_NOT_FOUND(404, "Class not found"),
	COURSE_SUGGESTION_NOT_FOUND(404, "Course suggestion not found"), ADMIN_NOT_FOUND(404, "Admin not found"),
	FACULTY_NOT_FOUND(404, "Faculty not found"), UNIVERSITY_NOT_FOUND(404, "University not found"),
	MATERIAL_NOT_FOUND(404, "Material not found"),
	BATCH_NOTIFICATION_NOT_FOUND(404, "Batch notification not found"),
	DEPARTMENT_NOT_FOUND(404, "Department not found"), ACCESS_TOKEN_NOT_FOUND(404, "Access token not found");

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
