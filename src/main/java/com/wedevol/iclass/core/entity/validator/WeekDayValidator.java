package com.wedevol.iclass.core.entity.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wedevol.iclass.core.entity.constraint.WeekDay;
import com.wedevol.iclass.core.enums.WeekDayType;

/**
 * Week Day Validator
 *
 * @author charz
 */
public class WeekDayValidator extends BaseValidator implements ConstraintValidator<WeekDay, String> {

	@Override
	public void initialize(WeekDay constraintAnnotation) {
		ignoreCase = constraintAnnotation.ignoreCase();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		final boolean isValid = Arrays.stream(WeekDayType.values())
										.filter(validOption -> equal(value, validOption.getDescription()))
										.findFirst()
										.isPresent();

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
					.addConstraintViolation();
		}

		return isValid;
	}
}