package com.wedevol.iclass.core.util;

import java.util.Arrays;
import java.util.function.Predicate;

import com.wedevol.iclass.core.entity.enums.ClassStatusType;
import com.wedevol.iclass.core.entity.enums.CourseSuggestionStatusType;
import com.wedevol.iclass.core.entity.enums.EnrollmentStatusType;

/**
 * Core Util Class
 *
 * @author charz
 */
public class CoreUtil {

	public static Predicate<String> isValidEnrollmentStatus() {
		return courseStatus -> Arrays
										.stream(EnrollmentStatusType.values())
											.filter(validOption -> courseStatus.equals(validOption.getDescription()))
											.findFirst()
											.isPresent();
	}

	public static boolean areValidEnrollmentStatusFilters(String statusFilter) {
		return Arrays.asList(statusFilter.split(",")).stream().allMatch(isValidEnrollmentStatus());
	}

	public static Predicate<String> isValidClassStatus() {
		return classStatus -> Arrays
									.stream(ClassStatusType.values())
										.filter(validOption -> classStatus.equals(validOption.getDescription()))
										.findFirst()
										.isPresent();
	}

	public static boolean areValidClassStatusFilters(String statusFilter) {
		return Arrays.asList(statusFilter.split(",")).stream().allMatch(isValidClassStatus());
	}

	public static Predicate<String> isValidCourseSuggestionStatus() {
		return courseSuggestionStatus -> Arrays
												.stream(CourseSuggestionStatusType.values())
													.filter(validOption -> courseSuggestionStatus.equals(
															validOption.getDescription()))
													.findFirst()
													.isPresent();
	}

	public static boolean areValidCourseSuggestionStatusFilters(String statusFilter) {
		return Arrays.asList(statusFilter.split(",")).stream().allMatch(isValidCourseSuggestionStatus());
	}
}
