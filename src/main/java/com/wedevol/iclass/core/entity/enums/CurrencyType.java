package com.wedevol.iclass.core.entity.enums;

/**
 * Currency Enum
 *
 * @author charz
 */
public enum CurrencyType {
	PEN("S/."), 
	USD("$");

	private final String description;

	CurrencyType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
