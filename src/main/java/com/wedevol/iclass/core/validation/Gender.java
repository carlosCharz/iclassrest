package com.wedevol.iclass.core.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;

/**
 * Gender Constraint
 *
 * @author charz
 */
@Size(min = 1, max = 1, message = "Gender string size must be 1")
@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {

	String message() default "Gender must be M or F";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	boolean ignoreCase() default false;
}