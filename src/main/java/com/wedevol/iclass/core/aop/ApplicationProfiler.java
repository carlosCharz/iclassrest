package com.wedevol.iclass.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Profiler that logs method names for the controllers and serviceImpls
 *
 * @author charz
 */

@Aspect
@Component
public class ApplicationProfiler {

	protected static final Logger logger = LoggerFactory.getLogger(ApplicationProfiler.class);

	@Around("execution(* com.wedevol.iclass.core.controller.*.*(..))")
	public Object aroundControllers(ProceedingJoinPoint pjp) throws Throwable {

		final Signature method = pjp.getSignature();
		final String packageName = method.getDeclaringTypeName();
		final String controllerName = packageName.substring(packageName.lastIndexOf(".") + 1);

		logger.info(controllerName + " -> " + method.getName());

		return pjp.proceed();
	}

}
