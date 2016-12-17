package com.wedevol.iclass.core.entity.enums;

/**
 * Week Day Enum
 *
 * @author charz
 */
public enum WeekDayType {
	MONDAY("mon"), TUESDAY("tue"), WEDNESDAY("wed"), THURSDAY("thu"), FRIDAY("fri"), SATURDAY("sat"), SUNDAY("sun");

	private final String description;

	WeekDayType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
