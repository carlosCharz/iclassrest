package com.wedevol.iclass.core.exception;

import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;

/**
 * Resource not found exception
 * 
 * @author charz
 *
 */
public class ResourceNotFoundException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;
	private NotFoundErrorType errorType;

	public ResourceNotFoundException(NotFoundErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}

	@Override
	public Integer getCode() {
		return this.errorType.getCode();
	}

}
