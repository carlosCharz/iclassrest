package com.wedevol.iclass.core.entity.validator;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wedevol.iclass.core.entity.constraint.PlaceOptions;
import com.wedevol.iclass.core.enums.PlaceOptionsType;

/**
 * Place Options Validator
 *
 * @author charz
 */
public class PlaceOptionsValidator extends BaseValidator implements ConstraintValidator<PlaceOptions, String> {

	@Override
	public void initialize(PlaceOptions constraintAnnotation) {
		ignoreCase = constraintAnnotation.ignoreCase();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		final Set<String> placeOptionsSet = value == null ? Collections.emptySet()
				: Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(value.split(","))));

		final Predicate<String> isValidOption = option -> Arrays.stream(PlaceOptionsType.values())
																.filter(validOption -> equal(option,
																		validOption.getDescription()))
																.findFirst()
																.isPresent();

		final boolean isValid = placeOptionsSet	.stream()
												.allMatch(isValidOption);

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context	.buildConstraintViolationWithTemplate(message)
					.addConstraintViolation();
		}

		return isValid;
	}
}