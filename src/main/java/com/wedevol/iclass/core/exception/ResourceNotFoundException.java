package com.wedevol.iclass.core.exception;

import com.wedevol.iclass.core.enums.NotFoundErrorType;

/**
 * Resource not found exception
 * 
 * @author charz
 *
 */
public class ResourceNotFoundException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(NotFoundErrorType errorType) {
		super(errorType.getCode(), errorType.getMessage());
	}

}
