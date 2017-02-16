package com.wedevol.iclass.core.interceptor;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.core.interception.PostMatchContainerRequestContext;
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

	public static final String HEADER_AUTHORIZATION = "authorization";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("--- Before Method Execution ---");
		HandlerMethod hm = (HandlerMethod) handler;
		Method method = hm.getMethod();

		if (method.isAnnotationPresent(Authorize.class)) {
			System.out.println(method.getAnnotation(Authorize.class)
										.basic());
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("--- Method executed ---");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("--- Request Completed ---");
	}

	private Long getUserIdFromUrl(HttpServletRequest request) {
		String path = request.getRequestURI()
								.substring(request.getContextPath()
													.length());
		Matcher matcher = Pattern.compile(".*/users/(?<userId>\\w+)/?.*")
									.matcher(path);
		if (!matcher.matches() || matcher.group("userId") == null || matcher.group("userId")
																			.isEmpty()) {
			return null;
		}
		return Long.valueOf(matcher.group("userId"));
	}

	private String getAccessToken(HttpServletRequest request) {
		String authorization = HEADER_AUTHORIZATION;
		return getHeaderParam(request, authorization);
	}

	private String getHeaderParam(HttpServletRequest request, String param) {
		System.out.println("Header:" + request.getHeader(param));
		return request.getHeader(param);
	}

}
