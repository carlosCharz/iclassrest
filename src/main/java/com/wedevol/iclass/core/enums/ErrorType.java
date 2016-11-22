package com.wedevol.iclass.core.enums;

/**
 * Error Type Enum
 *
 * @author charz
 */
public enum ErrorType {

	RESOURCE_NOT_FOUND(1, "40x", "Resource not found"), ARGUMENT_NOT_VALID(2, "40x", "Argument not valid"), VALIDATION_EXCEPTION(
			3, "40x", "User validation exception"), BAD_REQUEST_EXCEPTION(4, "40x", "Bad request exception");

	private final int code;
	private final String type;
	private final String message;

	ErrorType(int code, String type, String message) {
		this.code = code;
		this.type = type;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

}
