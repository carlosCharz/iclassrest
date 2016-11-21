package com.wedevol.iclass.core.enums;

/**
 * Error Type Enum
 *
 * @author charz
 */
public enum ErrorType {

	STUDENT_NOT_FOUND(1, "Student not found");

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
