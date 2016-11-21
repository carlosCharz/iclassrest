package com.wedevol.iclass.core.enums;

/**
 * Error Type Enum
 *
 * @author charz
 */
public enum ErrorType {

	RESOURCE_NOT_FOUND(1, "Resource not found"), ARGUMENT_NOT_VALID(2, "Argument not valid");

	private final int code;
	private final String message;

	ErrorType(int code, String message) {
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
