package com.wedevol.iclass.core.validation;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wedevol.iclass.core.enums.CourseStatusType;

/**
 * Course Status Validator
 *
 * @author charz
 */
public class CourseStatusValidator extends BaseValidator implements ConstraintValidator<CourseStatus, String> {

	@Override
	public void initialize(CourseStatus constraintAnnotation) {
		ignoreCase = constraintAnnotation.ignoreCase();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		final boolean isValid = Arrays	.stream(CourseStatusType.values())
										.filter(validOption -> equal(value, validOption.getDescription()))
										.findFirst()
										.isPresent();

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context	.buildConstraintViolationWithTemplate(message)
					.addConstraintViolation();
		}

		return isValid;
	}
}