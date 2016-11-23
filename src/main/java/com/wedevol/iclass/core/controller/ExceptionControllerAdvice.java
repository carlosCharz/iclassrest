package com.wedevol.iclass.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wedevol.iclass.core.entity.ErrorResponse;
import com.wedevol.iclass.core.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;

/**
 * Exception Controller Advice
 * 
 * @author charz
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorResponse resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		// Handles all resource not found exceptions types
		return new ErrorResponse(ex.getCode(), ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse badRequestExceptionHandler(BadRequestException ex) {
		// Handles all bad request exceptions types
		return new ErrorResponse(ex.getCode(), ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		return new ErrorResponse(BadRequestErrorType.ARGUMENT_NOT_VALID.getCode(), ex.getMessage());
	}
}
