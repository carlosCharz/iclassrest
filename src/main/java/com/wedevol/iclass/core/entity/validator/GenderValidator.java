package com.wedevol.iclass.core.entity.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wedevol.iclass.core.entity.constraint.Gender;
import com.wedevol.iclass.core.entity.enums.GenderType;

/**
 * Gender Input Validator
 *
 * @author charz
 */
public class GenderValidator extends BaseValidator implements ConstraintValidator<Gender, String> {

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

		final boolean isValid = Arrays.stream(GenderType.values())
										.filter(gender -> equal(value, gender.getDescription()))
										.findFirst()
										.isPresent();

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		}

		return isValid;
	}
}