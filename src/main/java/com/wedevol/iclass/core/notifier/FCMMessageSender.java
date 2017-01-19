package com.wedevol.iclass.core.notifier;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Priority;
import com.google.android.gcm.server.Notification;

/**
 * Class that sends messages to FCM to be sent to the client devices
 * 
 * @author Charz
 *
 */
public class FCMMessageSender implements IFCMMessageSender {

	protected static final Logger logger = LoggerFactory.getLogger(FCMMessageSender.class);

	// @Inject
	private FCMConnection connection;

	@Override
	public void send(NotificationRequest notification, String token) throws IOException {
		Message message = createMessage(notification);
		connection.send(message, token, 3/*
										 * Settings.fcm() .getRetries()
										 */);

	}

	@Override
	public void send(NotificationRequest notification, List<String> tokens) throws IOException {
		Message message = createMessage(notification);
		connection.send(message, tokens, 3/*
										 * Settings.fcm() .getRetries()
										 */);
	}

	private Message createMessage(NotificationRequest notification) {
		// final FcmConfiguration config = Settings.fcm();
		final String notificationType = notification.getNotificationTypeName();
		final Notification notificationPayload = createNotificationPayload(notification);
		Message message = new Message.Builder().collapseKey("xxx"/* config.getCollapseKey() */)
												.timeToLive(4/* config.getTimeToLive() */)
												.addData("senderId", String.valueOf(notification.getSenderId()))
												.addData("receiverId", String.valueOf(notification.getReceiverId()))
												.addData("type", notificationType)
												.addData("message", notification.getMessage())
												.notification(notificationPayload)
												.priority(Priority.HIGH)
												.build();
		logger.info(message.toString());
		return message;
	}

	private Notification createNotificationPayload(NotificationRequest notificationRequest) {
		final String message = notificationRequest.getContent()
													.get("message");
		final String notificationType = notificationRequest.getNotificationTypeName();
		return new Notification.Builder("").body(message)
											.badge(1)
											.clickAction(notificationType)
											.sound("default")
											.build();
	}

}
