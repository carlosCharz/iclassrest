package com.wedevol.iclass.core.exception;

import com.wedevol.iclass.core.enums.ErrorType;

/**
 * Bad request exception
 * 
 * @author charz
 *
 */
public class BadRequestException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(ErrorType errorType) {
		super(errorType.getCode(), errorType.getMessage());
	}

}
