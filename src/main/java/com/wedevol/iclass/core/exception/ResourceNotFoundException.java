package com.wedevol.iclass.core.exception;

import com.wedevol.iclass.core.enums.ErrorType;

/**
 * Resource not found exception
 * 
 * @author charz
 *
 */
public class ResourceNotFoundException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(ErrorType errorType) {
		super(errorType.getCode(), errorType.getMessage());
	}

}
