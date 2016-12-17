package com.wedevol.iclass.core.util;

import java.util.Arrays;
import java.util.function.Predicate;

import com.wedevol.iclass.core.entity.enums.CourseStatusType;

/**
 * Core Util Class
 *
 * @author charz
 */
public class CoreUtil {

	public static Predicate<String> isValidCourseStatus() {
		return courseStatus -> Arrays	.stream(CourseStatusType.values())
										.filter(validOption -> courseStatus.equals(validOption.getDescription()))
										.findFirst()
										.isPresent();
	}

	public static boolean areValidCourseStatusFilters(String statusFilter) {
		return Arrays	.asList(statusFilter.split(","))
						.stream()
						.allMatch(isValidCourseStatus());
	}
}
