package com.wedevol.iclass.core.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Bad request exception
 * 
 * @author charz
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int errorCode;
	private String message;

	public BadRequestException(int errorCode, String message) {
		super("Error code: " + errorCode + ". Message: " + message);
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
