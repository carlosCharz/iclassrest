package com.wedevol.iclass.core.notifier;

import java.util.List;

/**
 * Notification message content builder
 * 
 * @author charz
 */
public class MessageContentBuilder {

	public static String buildMessageContent(NotificationType notificationType, List<String> data) {
		MessageContent messageContent = new MessageContent();
		messageContent.setMessageBase(notificationType.getMessage());
		messageContent.setVariables(data);
		return messageContent.build();
	}
}
