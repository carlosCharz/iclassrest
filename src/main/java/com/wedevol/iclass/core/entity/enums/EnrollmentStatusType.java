package com.wedevol.iclass.core.entity.enums;

/**
 * User Enrollment Status Enum (for student or instructor)
 *
 * @author charz
 */
public enum EnrollmentStatusType {
	FREE("free"), OPEN("open"), PENDING_PAYMENT("pendingPayment"), VERIFYING_PAYMENT("verifyingPayment"),
	PAYED("payed");

	private final String description;

	EnrollmentStatusType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
