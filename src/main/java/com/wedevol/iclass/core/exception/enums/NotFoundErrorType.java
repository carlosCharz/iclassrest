package com.wedevol.iclass.core.exception.enums;

/**
 * Resource Not Found Error Type Enum (404 family)
 *
 * @author charz
 */
public enum NotFoundErrorType {
	STUDENT_NOT_FOUND(1, "Student not found"), 
	STUDENT_COURSE_NOT_FOUND(2, "Student enrollment not found"),
	COURSE_NOT_FOUND(3, "Course not found"), 
	TOPIC_NOT_FOUND(4, "Topic not found"),
	INSTRUCTOR_NOT_FOUND(5, "Instructor not found"),
	INSTRUCTOR_COURSE_NOT_FOUND(6, "Instructor enrollment not found"),
	INSTRUCTOR_SCHEDULE_NOT_FOUND(7, "Instructor schedule not found"), 
	CLASS_NOT_FOUND(8, "Class not found"),
	COURSE_SUGGESTION_NOT_FOUND(9, "Course suggestion not found"), 
	ADMIN_NOT_FOUND(10, "Admin not found"),
	FACULTY_NOT_FOUND(11, "Faculty not found"), 
	UNIVERSITY_NOT_FOUND(12, "University not found"),
	MATERIAL_NOT_FOUND(13, "Material not found"),
	BATCH_NOTIFICATION_NOT_FOUND(14, "Batch notification not found"),
	DEPARTMENT_NOT_FOUND(15, "Department not found"), 
	ACCESS_TOKEN_NOT_FOUND(16, "Access token not found"),
	PICTURE_URL_NOT_FOUND(17, "Picture url not found");

	private final int code;
	private final String message;

	private NotFoundErrorType(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

}
