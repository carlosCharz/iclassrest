package com.wedevol.iclass.core.exception.enums;

/**
 * Unauthorized Error Type Enum (401 family)
 *
 * @author charz
 */
public enum UnauthorizedErrorType {
	UNAUTHORIZED(400, "Unauthorized application access"), INCORRECT_CREDENTIALS(400, "Incorrect iclass credentials"),
	UNAUTHORIZED_DUE_TO_AUTHORIZATION_PARAM(400, "Missing authorization param in the header"),
	UNAUTHORIZED_DUE_TO_TOKEN_NOT_FOUND(400, "Access token not found"), 
	UNAUTHORIZED_DUE_TO_MISMATCH_USER(400, "The user that makes the request does not match with the access token associated with him");

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
