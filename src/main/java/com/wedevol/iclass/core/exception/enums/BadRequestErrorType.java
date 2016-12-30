package com.wedevol.iclass.core.exception.enums;

/**
 * Validation Error Type Enum (4xx family)
 *
 * @author charz
 */
public enum BadRequestErrorType {
	BAD_REQUEST_EXCEPTION(400, "Bad request exception"), ARGUMENT_NOT_VALID(401,
			"Argument not valid"), WRONG_DESERIALIZATION(402,
					"Wrong deserialization to build the bean"), METHOD_NOT_ALLOWED(403,
							"Method not allowed"), COURSE_STATUS_NOT_VALID(404,
									"Course status not valid"), ARGUMENT_TYPE_MISMATCH(405,
											"Argument type mismatch"), DATETIMES_NOT_VALID(406,
													"Start time should be less than the end time");

	private final int code;
	private final String message;

	BadRequestErrorType(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
