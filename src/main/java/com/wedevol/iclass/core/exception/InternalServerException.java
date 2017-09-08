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
	private ServerErrorType errorType;

	public InternalServerException(ServerErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}

	@Override
	public Integer getCode() {
		return this.errorType.getCode();
	}

}
