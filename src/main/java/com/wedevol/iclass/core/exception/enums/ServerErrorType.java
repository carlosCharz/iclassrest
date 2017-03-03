package com.wedevol.iclass.core.exception.enums;

/**
 * Server Error Type Enum (500 family)
 *
 * @author charz
 */
public enum ServerErrorType {
	NOT_IMPLEMENTED(500, "Method not implemented"), INTERNAL_SERVER_ERROR(500, "Internal server error"),
	CANNOT_GET_INPUT_STREAM(500, "Cannot get the input stream from the multipart file");

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
