package com.wedevol.iclass.core.exception;

import com.wedevol.iclass.core.exception.enums.UnauthorizedErrorType;

/**
 * Unauthorized exception
 * 
 * @author charz
 *
 */
public class UnauthorizedException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException(UnauthorizedErrorType errorType) {
		super(errorType.getCode(), errorType.getMessage());
	}

}
