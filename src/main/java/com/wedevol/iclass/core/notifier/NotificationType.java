package com.wedevol.iclass.core.notifier;

/**
 * Notification type
 * 
 * @author charz
 */
public enum NotificationType {

	DIRECT_MESSAGE("Direct message!"),
	WELCOME_STUDENT("Bienvenido a iClass! Tienes un curso gratis para este semestre!"),
	WELCOME_INSTRUCTOR("Bienvenido a iClass!"),
	CLASS_CONFIRMED_FOR_STUDENT("%s ha confirmado la asesoría del curso %s!"),
	CLASS_REJECTED_FOR_STUDENT("%s ha rechazado la asesoría del curso %s!"),
	NEW_CLASS_REQUEST_FOR_INSTRUCTOR("%s ha solicitado una asesoría para el curso %s!");

	private String message;

	NotificationType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
