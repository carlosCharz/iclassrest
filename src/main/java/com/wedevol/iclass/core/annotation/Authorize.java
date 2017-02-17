package com.wedevol.iclass.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Authorize Annotation for the controllers
 *
 * @author Charz++
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorize {

	boolean basic() default false;

	boolean hard() default false;

}
