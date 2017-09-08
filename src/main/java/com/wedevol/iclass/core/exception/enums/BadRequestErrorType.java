package com.wedevol.iclass.core.exception.enums;

/**
 * Bad Request Error Type Enum (400 family)
 *
 * @author charz
 */
public enum BadRequestErrorType {
	BAD_REQUEST_EXCEPTION(1, "Bad request exception"), 
	ARGUMENT_NOT_VALID(2, "Argument not valid"),
	WRONG_DESERIALIZATION(3, "Wrong deserialization to build the bean"),
	METHOD_NOT_ALLOWED(4, "Method not allowed"), 
	COURSE_STATUS_NOT_VALID(5, "Course status not valid"),
	ARGUMENT_TYPE_MISMATCH(6, "Argument type mismatch"),
	DATETIMES_NOT_VALID(7, "Start time should be less than the end time"),
	MISSING_PARAMETER(8, "Missing parameter exception"), 
	CLASS_STATUS_NOT_VALID(9, "Class status not valid"),
	USER_ALREADY_EXISTS(10, "The user already exists"),
	ENROLLMENT_ALREADY_EXISTS(11, "The enrollment already exists"),
	COURSE_ALREADY_EXISTS(12, "The course already exists"), 
	TOPIC_ALREADY_EXISTS(13, "The topic already exists"),
	USER_TYPE_NOT_VALID(14, "User type not valid"), 
	FIELDS_MISSING(15, "Fields missing"),
	UNIVERSITY_ALREADY_EXISTS(16, "The university already exists"),
	MATERIAL_ALREADY_EXISTS(17, "The material already exists"),
	FACULTY_ALREADY_EXISTS(18, "The faculty already exists"),
	DEPARTMENT_ALREADY_EXISTS(19, "The department already exists"),
	ACCESS_TOKEN_ALREADY_EXISTS(20, "The access token already exists"),
	USER_ID_FROM_URL_INVALID(21, "There is no userId in the url"),
	USER_TYPE_FROM_URL_INVALID(22, "Invalid userType (students, instructors or admins) in the url"),
	EXCEEDED_MULTIPART_MAX_FILE_SIZE(23, "The file exceeds the maximum size permitted"),
	EMPTY_MULTIPART_FILE_SIZE(24, "The file is empty");

	private final int code;
	private final String message;

	private BadRequestErrorType(int code, String message) {
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
