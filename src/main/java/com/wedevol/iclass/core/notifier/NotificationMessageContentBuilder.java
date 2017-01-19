package com.wedevol.iclass.core.notifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Notification message content builder
 * 
 * @author charz
 */
public class NotificationMessageContentBuilder {

	public String buildMessageContent(NotificationType notificationType, List<String> data) {

		if (notificationType == null) {
			// TODO: throw exception
		}

		NotificationMessageContent messageContent = new NotificationMessageContent();
		messageContent.setMessageBase(notificationType.getMessageContent());
		messageContent.setVariables(data == null ? new ArrayList<String>() : data);
		return messageContent.build();
	}
}
