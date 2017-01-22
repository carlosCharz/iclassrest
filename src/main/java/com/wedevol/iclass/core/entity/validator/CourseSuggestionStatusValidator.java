package com.wedevol.iclass.core.entity.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wedevol.iclass.core.entity.constraint.CourseSuggestionStatus;
import com.wedevol.iclass.core.entity.enums.CourseSuggestionStatusType;

/**
 * Course Suggestion Status Validator
 *
 * @author charz
 */
public class CourseSuggestionStatusValidator extends BaseValidator implements ConstraintValidator<CourseSuggestionStatus, String> {

	@Override
	public void initialize(CourseSuggestionStatus constraintAnnotation) {
		ignoreCase = constraintAnnotation.ignoreCase();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		final boolean isValid = Arrays
										.stream(CourseSuggestionStatusType.values())
											.filter(validOption -> equal(value, validOption.getDescription()))
											.findFirst()
											.isPresent();

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		}

		return isValid;
	}
}