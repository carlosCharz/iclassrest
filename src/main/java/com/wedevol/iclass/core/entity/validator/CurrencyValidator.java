package com.wedevol.iclass.core.entity.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wedevol.iclass.core.entity.constraint.Currency;
import com.wedevol.iclass.core.entity.enums.CurrencyType;

/**
 * Currency Validator
 *
 * @author charz
 */
public class CurrencyValidator extends BaseValidator implements ConstraintValidator<Currency, String> {

	@Override
	public void initialize(Currency constraintAnnotation) {
		ignoreCase = constraintAnnotation.ignoreCase();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		final boolean isValid = Arrays	.stream(CurrencyType.values())
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