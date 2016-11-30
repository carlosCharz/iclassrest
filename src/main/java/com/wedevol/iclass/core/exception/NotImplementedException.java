package com.wedevol.iclass.core.exception;

import com.wedevol.iclass.core.enums.ServerErrorType;

/**
 * Not implemented exception
 * 
 * @author charz
 *
 */
public class NotImplementedException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	public NotImplementedException(ServerErrorType errorType) {
		super(errorType.getCode(), errorType.getMessage());
	}

}
