package com.wedevol.iclass.core.entity.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.wedevol.iclass.core.entity.validator.CurrencyValidator;

/**
 * Currency Constraint
 *
 * @author charz
 */
@Documented
@Constraint(validatedBy = CurrencyValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Currency {

	String message() default "Currency must be S/. or $";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	boolean ignoreCase() default false;
}