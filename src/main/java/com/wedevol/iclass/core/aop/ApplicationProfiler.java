package com.wedevol.iclass.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
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

		logger.info("Controller -> " + pjp	.getSignature()
											.getName());

		return pjp.proceed();
	}

	@Around("execution(* com.wedevol.iclass.core.service.impl.*.*(..))")
	public Object aroundServiceImpls(ProceedingJoinPoint pjp) throws Throwable {

		logger.info("ServiceImpl -> " + pjp	.getSignature()
											.getName());

		return pjp.proceed();
	}

}
