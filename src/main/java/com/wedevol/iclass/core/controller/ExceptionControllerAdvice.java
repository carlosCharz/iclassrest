package com.wedevol.iclass.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wedevol.iclass.core.entity.ErrorResponse;
import com.wedevol.iclass.core.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.NotImplementedException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;

/**
 * Exception Controller Advice
 * 
 * @author charz
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

	// Custom exception handlers
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
	@ExceptionHandler(NotImplementedException.class)
	@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
	public ErrorResponse notImplementedExceptionHandler(NotImplementedException ex) {
		// Handles all not implemented exceptions types
		return new ErrorResponse(ex.getCode(), ex.getMessage());
	}

	// Spring exception handlers
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		return new ErrorResponse(BadRequestErrorType.ARGUMENT_NOT_VALID.getCode(), ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse methodArgumentNotValidExceptionHandler(HttpMessageNotReadableException ex) {
		return new ErrorResponse(BadRequestErrorType.WRONG_DESERIALIZATION.getCode(), ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
		return new ErrorResponse(BadRequestErrorType.METHOD_NOT_ALLOWED.getCode(), ex.getMessage());
	}

}
