package com.wedevol.iclass.core.constraint;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wedevol.iclass.core.enums.GenderType;

/**
 * Gender Input Validator
 *
 * @author charz
 */
public class GenderValidator implements ConstraintValidator<Gender, String> {

	private boolean ignoreCase;
	private String message;

	@Override
	public void initialize(Gender constraintAnnotation) {
		ignoreCase = constraintAnnotation.ignoreCase();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		final boolean isValid = Arrays	.stream(GenderType.values())
										.filter(gender -> equalGender(gender.getDescription(), value))
										.findFirst()
										.isPresent();

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context	.buildConstraintViolationWithTemplate(message)
					.addConstraintViolation();
		}

		return isValid;
	}

	private boolean equalGender(String value1, String value2) {
		return ignoreCase ? value1.equalsIgnoreCase(value2) : value1.equals(value2);
	}
}