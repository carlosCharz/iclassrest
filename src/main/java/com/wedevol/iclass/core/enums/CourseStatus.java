package com.wedevol.iclass.core.enums;

/**
 * User Course Status Enum (for student or instructor)
 *
 * @author charz
 */
public enum CourseStatus {
	FREE("free"), OPEN("open"), PENDING_PAYMENT("pendingPayment"), VERIFYING_PAYMENT("verifyingPayment"), PAYED(
			"payed");

	private final String description;

	CourseStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
