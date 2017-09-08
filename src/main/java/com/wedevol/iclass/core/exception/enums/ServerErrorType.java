package com.wedevol.iclass.core.exception.enums;

/**
 * Server Error Type Enum (500 family)
 *
 * @author charz
 */
public enum ServerErrorType {
	NOT_IMPLEMENTED(1, "Method not implemented"), 
	INTERNAL_SERVER_ERROR(2, "Internal server error"),
	CANNOT_GET_INPUT_STREAM(3, "Cannot get the input stream from the multipart file"),
	CANNOT_RESIZE_THE_PHOTO(4, "Cannot resize the photo");

	private final int code;
	private final String message;

	private ServerErrorType(int code, String message) {
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
