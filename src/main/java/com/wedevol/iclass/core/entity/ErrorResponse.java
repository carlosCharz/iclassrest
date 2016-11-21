package com.wedevol.iclass.core.entity;

/**
 * Error Response
 *
 * @author charz
 */
public class ErrorResponse {

	private int errorCode;
	private String errorMessage;

	public ErrorResponse(int errorCode, String message) {
		this.errorCode = errorCode;
		this.errorMessage = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
