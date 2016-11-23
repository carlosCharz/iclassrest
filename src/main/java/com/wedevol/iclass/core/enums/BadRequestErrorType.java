package com.wedevol.iclass.core.enums;

/**
 * Validation Error Type Enum: 40x
 *
 * @author charz
 */
public enum BadRequestErrorType {
	BAD_REQUEST_EXCEPTION(400, "Bad request exception"), ARGUMENT_NOT_VALID(401, "Argument not valid"), VALIDATION_EXCEPTION(
			402, "User validation exception");

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
