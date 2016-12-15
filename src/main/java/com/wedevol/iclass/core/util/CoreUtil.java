package com.wedevol.iclass.core.util;

import java.util.Arrays;
import java.util.function.Predicate;

import com.wedevol.iclass.core.enums.CourseStatusType;

/**
 * Core Util Class
 *
 * @author charz
 */
public class CoreUtil {

	public static Predicate<String> isValidCourseStatus() {
		return course -> Arrays	.stream(CourseStatusType.values())
								.filter(validOption -> course.equals(validOption.getDescription()))
								.findFirst()
								.isPresent();
	}

	public static boolean validateCourseStatusFilters(String statusFilter) {
		return Arrays	.asList(statusFilter.split(","))
						.stream()
						.allMatch(isValidCourseStatus());
	}
}
