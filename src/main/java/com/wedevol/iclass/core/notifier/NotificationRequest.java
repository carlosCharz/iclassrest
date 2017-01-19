package com.wedevol.iclass.core.notifier;

/**
 * Notification Request for the FCM. This is the data payload.
 * 
 * @author charz
 *
 */
public class NotificationRequest {

	private Long senderId;
	private Long receiverId;
	private String message;
	private NotificationType notificationType;

	/**
	 * Create a notification request for FCM
	 * 
	 * @param senderId
	 * @param receiverId
	 * @param message
	 * @param notificationType
	 */
	public NotificationRequest(Long senderId, Long receiverId, String message, NotificationType notificationType) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.message = message;
		this.notificationType = notificationType;
	}

	public String getMessage() {
		return message;
	}

	public Long getSenderId() {
		return senderId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public String getNotificationTypeName() {
		return notificationType.name();
	}

}
