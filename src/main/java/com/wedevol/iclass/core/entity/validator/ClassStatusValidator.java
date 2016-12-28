package com.wedevol.iclass.core.entity.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wedevol.iclass.core.entity.constraint.ClassStatus;
import com.wedevol.iclass.core.entity.enums.ClassStatusType;

/**
 * Class Status Validator
 *
 * @author charz
 */
public class ClassStatusValidator extends BaseValidator implements ConstraintValidator<ClassStatus, String> {

	@Override
	public void initialize(ClassStatus constraintAnnotation) {
		ignoreCase = constraintAnnotation.ignoreCase();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		final boolean isValid = Arrays
										.stream(ClassStatusType.values())
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