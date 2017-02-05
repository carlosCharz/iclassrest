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
	CLASS_CONFIRMED_FOR_STUDENT("El asesor %s ha confirmado tu solicitud de asesoría del curso %s! Revísalo!"),
	CLASS_REJECTED_FOR_STUDENT("El asesor %s ha rechazado tu solicitud de asesoría del curso %s! Revísalo!"),
	CLASS_CANCELLED_FOR_INSTRUCTOR("El alumno %s ha cancelado la asesoría del curso %s! Revísalo!"),
	NEW_CLASS_REQUEST_FOR_INSTRUCTOR("El alumno %s ha solicitado una asesoría del curso %s! Confírmala!"),
	NEW_COURSE_APPROVED_FOR_INSTRUCTOR(
			"El curso %s ha sido aprovado! Solo te falta pagarlo para comenzar a dictar clases! Por promoción será gratuito!"),
	NEW_COURSE_DENIED_FOR_INSTRUCTOR("El curso %s ha sido denegado! Todavía tienes chances de dictar otro curso!"),
	CLASS_COMING_SOON("Tienes una asesoría del curso %s a las %s horas!"),
	@Deprecated FINISHED_CLASS_TO_RATE_FOR_STUDENT("Tu asesoría del curso %s con %s ha terminado. Califícalo!"),
	FINISHED_CLASS_RATING("Has recibido un rating de %s/5.0 para el curso %s!");

	private String message;

	NotificationType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
