package com.wedevol.iclass.core.exception.enums;

/**
 * Unauthorized Error Type Enum (401 family)
 *
 * @author charz
 */
public enum UnauthorizedErrorType {
	UNAUTHORIZED(400, "Unauthorized exception"), INCORRECT_CREDENTIALS(400, "Incorrect iclass credentials");

	private final int code;
	private final String message;

	UnauthorizedErrorType(int code, String message) {
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
