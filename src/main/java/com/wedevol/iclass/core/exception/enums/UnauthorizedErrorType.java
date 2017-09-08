package com.wedevol.iclass.core.exception.enums;

/**
 * Unauthorized Error Type Enum (401 family)
 *
 * @author charz
 */
public enum UnauthorizedErrorType {
	UNAUTHORIZED(1, "Unauthorized application access"),
	INCORRECT_CREDENTIALS(2, "Unauthorized! Incorrect iclass credentials"),
	UNAUTHORIZED_DUE_TO_AUTHORIZATION_PARAM(3, "Missing authorization param in the header"),
	UNAUTHORIZED_DUE_TO_TOKEN_NOT_FOUND(4, "Unauthorized! Access token not found!"),
	UNAUTHORIZED_DUE_TO_MISMATCH_USER(5, "Unauthorized! The user that makes the request does not match with the access token associated with him!");

	private final int code;
	private final String message;

	private UnauthorizedErrorType(int code, String message) {
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
