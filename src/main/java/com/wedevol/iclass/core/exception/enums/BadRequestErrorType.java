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
	DATETIMES_NOT_VALID(400, "Start time should be less than the end time");

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
