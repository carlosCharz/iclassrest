package com.wedevol.iclass.core.entity.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wedevol.iclass.core.entity.constraint.EnrollmentStatus;
import com.wedevol.iclass.core.entity.enums.EnrollmentStatusType;

/**
 * Enrollment Status Validator
 *
 * @author charz
 */
public class EnrollmentStatusValidator extends BaseValidator implements ConstraintValidator<EnrollmentStatus, String> {

	@Override
	public void initialize(EnrollmentStatus constraintAnnotation) {
		ignoreCase = constraintAnnotation.ignoreCase();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		final boolean isValid = Arrays.stream(EnrollmentStatusType.values())
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