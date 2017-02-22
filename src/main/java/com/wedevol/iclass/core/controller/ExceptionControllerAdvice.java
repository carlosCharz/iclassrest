package com.wedevol.iclass.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import com.wedevol.iclass.core.entity.ErrorResponse;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.InternalServerException;
import com.wedevol.iclass.core.exception.NotImplementedException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.UnauthorizedException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;

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

	@ResponseBody
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ErrorResponse unauthorizedExceptionHandler(UnauthorizedException ex) {
		// Handles all unauthorized exceptions types
		return new ErrorResponse(ex.getCode(), ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(InternalServerException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse internalServerErrorExceptionHandler(InternalServerException ex) {
		// Handles all internal server exceptions types
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

	@ResponseBody
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
		return new ErrorResponse(BadRequestErrorType.ARGUMENT_TYPE_MISMATCH.getCode(), ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
		return new ErrorResponse(BadRequestErrorType.MISSING_PARAMETER.getCode(), ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse missingServletRequestParameterExceptionHandler(MultipartException ex) {
		return new ErrorResponse(BadRequestErrorType.EXCEEDED_MULTIPART_MAX_FILE_SIZE.getCode(), ex.getMessage());
	}

}
