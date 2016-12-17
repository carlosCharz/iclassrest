package com.wedevol.iclass.core.exception;

import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;

/**
 * Bad request exception
 * 
 * @author charz
 *
 */
public class BadRequestException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(BadRequestErrorType errorType) {
		super(errorType.getCode(), errorType.getMessage());
	}

}
