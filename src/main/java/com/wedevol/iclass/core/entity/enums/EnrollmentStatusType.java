package com.wedevol.iclass.core.entity.enums;

/**
 * User Enrollment Status Enum (for student or instructor)
 *
 * @author charz
 */
public enum EnrollmentStatusType {
	FREE("free"), 
	REQUESTED("requested"), 
	PENDING_PAYMENT("pendingPayment"), 
	VERIFYING_PAYMENT("verifyingPayment"),
	PAYED("payed"), 
	DENIED("denied");

	private final String description;

	EnrollmentStatusType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
