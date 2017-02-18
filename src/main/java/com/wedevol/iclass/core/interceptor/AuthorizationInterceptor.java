package com.wedevol.iclass.core.interceptor;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wedevol.iclass.core.annotation.Authorize;
import com.wedevol.iclass.core.entity.AccessToken;
import com.wedevol.iclass.core.entity.enums.UserType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.UnauthorizedException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.UnauthorizedErrorType;
import com.wedevol.iclass.core.service.AccessTokenService;

/**
 * Authorization Interceptor
 *
 * @author Charz++
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

	public static final String HEADER_AUTHORIZATION = "authorization";

	protected static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

	@Autowired
	private AccessTokenService accessTokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO: remove logger
		logger.info("--- Before Method Execution ---");
		final HandlerMethod handlerMethod = (HandlerMethod) handler;
		final Method method = handlerMethod.getMethod();
		if (method.isAnnotationPresent(Authorize.class)) {
			validateAuthorization(request, method);
		}
		return true;
	}

	private void validateAuthorization(HttpServletRequest request, final Method method) {
		final boolean basicValue = method.getAnnotation(Authorize.class)
											.basic();
		final boolean hardValue = method.getAnnotation(Authorize.class)
										.hard();
		logger.info("basic: " + basicValue + " hard: " + hardValue);
		final String authParam = getAuthorizationParamOrThrow(request);
		logger.info("Header:" + authParam);
		if (basicValue) {
			// Check that the token exists
			this.findByTokenOrThrow(authParam);
		} else if (hardValue) {
			// Check that the token exists and the userId matches
			final AccessToken accessToken = this.findByTokenOrThrow(authParam);
			final Long userId = getUserIdFromUrl(request);
			logger.info("UserId:" + userId);
			final UserType userType = getUserTypeFromUrl(request);
			logger.info("UserType:" + userType.getDescription());
			if (!accessToken.getToken()
							.equals(accessToken) || !accessToken.getUserId()
																.equals(userId)
					|| !accessToken.getUserType()
									.equals(userType.getDescription())) {
				throw new UnauthorizedException(UnauthorizedErrorType.UNAUTHORIZED_DUE_TO_MISMATCH_USER);
			}
		}
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
		String path = request.getRequestURI()
								.substring(request.getContextPath()
													.length());
		Matcher matcher = Pattern.compile(".*/(instructors|students)/(?<userId>\\w+)/?.*")
									.matcher(path);
		if (!matcher.matches() || matcher.group("userId") == null || matcher.group("userId")
																			.isEmpty()) {
			throw new BadRequestException(BadRequestErrorType.USER_ID_FROM_URL_INVALID);
		}
		return Long.valueOf(matcher.group("userId"));
	}

	private UserType getUserTypeFromUrl(HttpServletRequest request) {
		final String path = request.getRequestURI()
									.substring(request.getContextPath()
														.length());
		final Matcher matcherInstructor = Pattern.compile(".*/instructors/")
													.matcher(path);
		final Matcher matcherStudent = Pattern.compile(".*/students/")
												.matcher(path);
		final Matcher matcherAdmin = Pattern.compile(".*/admins/")
											.matcher(path);
		if (matcherInstructor.find()) {
			return UserType.INSTRUCTOR;
		} else if (matcherStudent.find()) {
			return UserType.STUDENT;
		} else if (matcherAdmin.find()) {
			return UserType.ADMIN;
		}
		throw new BadRequestException(BadRequestErrorType.USER_TYPE_FROM_URL_INVALID);
	}

	private String getAuthorizationParam(HttpServletRequest request) {
		final String authorization = HEADER_AUTHORIZATION;
		return getHeaderParam(request, authorization);
	}

	private String getHeaderParam(HttpServletRequest request, String param) {
		return request.getHeader(param);
	}

	private String getAuthorizationParamOrThrow(HttpServletRequest request) {
		// TODO: test null
		Optional<String> authObj = Optional.ofNullable(getAuthorizationParam(request));
		return authObj.orElseThrow(() -> new UnauthorizedException(
				UnauthorizedErrorType.UNAUTHORIZED_DUE_TO_AUTHORIZATION_PARAM));
	}

	public AccessToken findByTokenOrThrow(String token) {
		final Optional<AccessToken> tokenObj = Optional.ofNullable(accessTokenService.findByToken(token));
		return tokenObj.orElseThrow(() -> new UnauthorizedException(
				UnauthorizedErrorType.UNAUTHORIZED_DUE_TO_TOKEN_NOT_FOUND));
	}

}
