package com.wedevol.iclass.core.notifier;

/**
 * Notification type
 * 
 * @author charz
 */
public enum NotificationType {

	CLASS_CONFIRMED("%s has confirmed the class."), CLASS_REJECTED("%s has rejected the class.");

	private String messageContent;

	NotificationType(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageContent() {
		return messageContent;
	}
}
