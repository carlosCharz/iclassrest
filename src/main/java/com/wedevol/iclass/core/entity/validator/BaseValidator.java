package com.wedevol.iclass.core.entity.validator;

/**
 * Base Input Validator
 *
 * @author charz
 */
public class BaseValidator {

	protected boolean ignoreCase = false;
	protected String message;

	public boolean equal(String value1, String value2) {
		return ignoreCase ? value1.equalsIgnoreCase(value2) : value1.equals(value2);
	}
}