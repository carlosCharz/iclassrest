package com.wedevol.iclass.core.entity.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.wedevol.iclass.core.entity.validator.ClassStatusValidator;

/**
 * Class Status Constraint
 *
 * @author charz
 */
@Documented
@Constraint(validatedBy = ClassStatusValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassStatus {

	String message() default "Class status must be requested, confirmed, rejected and ignored";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	boolean ignoreCase() default false;
}