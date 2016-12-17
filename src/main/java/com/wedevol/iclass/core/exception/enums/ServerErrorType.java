package com.wedevol.iclass.core.exception.enums;

/**
 * Server Error Type Enum (5xx family)
 *
 * @author charz
 */
public enum ServerErrorType {
	NOT_IMPLEMENTED(500, "Method not implemented");

	private final int code;
	private final String message;

	ServerErrorType(int code, String message) {
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
