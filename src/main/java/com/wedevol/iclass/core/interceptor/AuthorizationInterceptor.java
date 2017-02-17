package com.wedevol.iclass.core.interceptor;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wedevol.iclass.core.annotation.Authorize;

/**
 * Authorization Interceptor
 *
 * @author Charz++
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

	protected static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

	public static final String HEADER_AUTHORIZATION = "authorization";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO: remove logger
		logger.info("--- Before Method Execution ---");
		final HandlerMethod handlerMethod = (HandlerMethod) handler;
		final Method method = handlerMethod.getMethod();
		if (method.isAnnotationPresent(Authorize.class)) {
			final boolean basicValue = method.getAnnotation(Authorize.class).basic();
			final boolean hardValue = method.getAnnotation(Authorize.class).hard();
			logger.info("basic: " + basicValue + " hard: " + hardValue);
			final String accessToken = getAccessToken(request);
			logger.info("Header:" + accessToken);
			final Long userId = getUserIdFromUrl(request);
			logger.info("UserId:" + userId);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	private Long getUserIdFromUrl(HttpServletRequest request) {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		Matcher matcher = Pattern.compile(".*/(instructors|students)/(?<userId>\\w+)/?.*").matcher(path);
		if (!matcher.matches() || matcher.group("userId") == null || matcher.group("userId").isEmpty()) {
			return null;
		}
		return Long.valueOf(matcher.group("userId"));
	}

	private String getAccessToken(HttpServletRequest request) {
		String authorization = HEADER_AUTHORIZATION;
		return getHeaderParam(request, authorization);
	}

	private String getHeaderParam(HttpServletRequest request, String param) {
		return request.getHeader(param);
	}

}
