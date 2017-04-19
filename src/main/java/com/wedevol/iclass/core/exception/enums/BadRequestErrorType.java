package com.wedevol.iclass.core.exception.enums;

/**
 * Bad Request Error Type Enum (400 family)
 *
 * @author charz
 */
public enum BadRequestErrorType {
	BAD_REQUEST_EXCEPTION(400, "Bad request exception"), ARGUMENT_NOT_VALID(400, "Argument not valid"),
	WRONG_DESERIALIZATION(400, "Wrong deserialization to build the bean"),
	METHOD_NOT_ALLOWED(400, "Method not allowed"), COURSE_STATUS_NOT_VALID(400, "Course status not valid"),
	ARGUMENT_TYPE_MISMATCH(400, "Argument type mismatch"),
	DATETIMES_NOT_VALID(400, "Start time should be less than the end time"),
	MISSING_PARAMETER(400, "Missing parameter exception"), CLASS_STATUS_NOT_VALID(400, "Class status not valid"),
	USER_ALREADY_EXISTS(400, "The user already exists"),
	ENROLLMENT_ALREADY_EXISTS(400, "The enrollment already exists"),
	COURSE_ALREADY_EXISTS(400, "The course already exists"), TOPIC_ALREADY_EXISTS(400, "The topic already exists"),
	USER_TYPE_NOT_VALID(400, "User type not valid"), FIELDS_MISSING(400, "Fields missing"),
	UNIVERSITY_ALREADY_EXISTS(400, "The university already exists"),
	MATERIAL_ALREADY_EXISTS(400, "The material already exists"),
	FACULTY_ALREADY_EXISTS(400, "The faculty already exists"),
	DEPARTMENT_ALREADY_EXISTS(400, "The department already exists"),
	ACCESS_TOKEN_ALREADY_EXISTS(400, "The access token already exists"),
	USER_ID_FROM_URL_INVALID(400, "There is no userId in the url"),
	USER_TYPE_FROM_URL_INVALID(400, "Invalid userType (students, instructors or admins) in the url"),
	EXCEEDED_MULTIPART_MAX_FILE_SIZE(400, "The file exceeds the maximum size permitted"),
	EMPTY_MULTIPART_FILE_SIZE(400, "The file is empty");

	private final int code;
	private final String message;

	BadRequestErrorType(int code, String message) {
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
