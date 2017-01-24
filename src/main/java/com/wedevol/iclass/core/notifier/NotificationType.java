package com.wedevol.iclass.core.notifier;

/**
 * Notification type
 * 
 * @author charz
 */
public enum NotificationType {

	DIRECT_MESSAGE("Direct message!"), WELCOME_STUDENT("Bienvenido a iClass! Tienes un curso gratis para este semestre."),
	WELCOME_INSTRUCTOR("Bienvenido a iClass!"), CLASS_CONFIRMED("%s ha confirmado la clase."),
	CLASS_REJECTED("%s ha rechazado la clase.");

	private String message;

	NotificationType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
