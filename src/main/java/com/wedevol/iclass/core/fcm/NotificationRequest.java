package com.wedevol.iclass.core.fcm;

/**
 * Notification Request for the FCM. This is the data payload.
 * 
 * @author charz
 *
 */
public class NotificationRequest {

	private String message;
	private NotificationType notificationType;

	/**
	 * Create a notification request for FCM
	 * 
	 * @param message
	 * @param notificationType
	 */
	public NotificationRequest(String message, NotificationType notificationType) {
		this.message = message;
		this.notificationType = notificationType;
	}

	public String getMessage() {
		return message;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public String getNotificationTypeName() {
		return notificationType.name();
	}

}
