package com.wedevol.iclass.core.exception;

import com.wedevol.iclass.core.exception.enums.ServerErrorType;

/**
 * Internal server exception
 * 
 * @author charz
 *
 */
public class InternalServerException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	public InternalServerException(ServerErrorType errorType) {
		super(errorType.getCode(), errorType.getMessage());
	}

}
