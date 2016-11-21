package com.wedevol.iclass.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wedevol.iclass.core.entity.ErrorResponse;
import com.wedevol.iclass.core.enums.ErrorType;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;

/**
 * Exception Controller Advice
 * 
 * @author charz
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorResponse resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		return new ErrorResponse(ex.getCode(), ex.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		return new ErrorResponse(ErrorType.ARGUMENT_NOT_VALID.getCode(), ex.getMessage());
	}
}
