package com.wedevol.iclass.core.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Course Status Constraint
 *
 * @author charz
 */
@Documented
@Constraint(validatedBy = CourseStatusValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseStatus {

	String message() default "Course status must be free, open, pendingPayment, verifyingPayment, payed";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	boolean ignoreCase() default false;
}