package com.wedevol.iclass.core.notifier;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Priority;
import com.google.android.gcm.server.Notification;
import com.wedevol.iclass.core.configuration.FcmSetting;

/**
 * Class that sends messages to FCM to be sent to the client devices
 * 
 * @author charz
 *
 */
@Component
public class FCMMessageSender implements IFCMMessageSender {

	protected static final Logger logger = LoggerFactory.getLogger(FCMMessageSender.class);

	@Autowired
	private FCMConnection connection;

	@Autowired
	private FcmSetting fcmSetting;

	@Override
	public void send(NotificationRequest notification, String token) {
		Message message = createMessage(notification);
		try {
			connection.send(message, token, fcmSetting.getRetries());
		} catch (IOException e) {
			logger.info("The message could not be sent due to a FCM connection error!");
		}

	}

	@Override
	public void send(NotificationRequest notification, List<String> tokens) {
		Message message = createMessage(notification);
		try {
			connection.send(message, tokens, fcmSetting.getRetries());
		} catch (IOException e) {
			logger.info("The message could not be sent due to a FCM connection error!");
		}
	}

	private Message createMessage(NotificationRequest notification) {
		final String notificationType = notification.getNotificationTypeName();
		final Notification notificationPayload = createNotificationPayload(notification);
		Message message = new Message.Builder()
												.collapseKey(fcmSetting.getCollapseKey())
												.timeToLive(fcmSetting.getTimeToLive())
												.addData("type", notificationType)
												.addData("message", notification.getMessage())
												.notification(notificationPayload)
												.priority(Priority.HIGH)
												.build();
		logger.info("Full message attributes: {}", message.toString());
		return message;
	}

	private Notification createNotificationPayload(NotificationRequest notificationRequest) {
		final String message = notificationRequest.getMessage();
		final String notificationType = notificationRequest.getNotificationTypeName();
		return new Notification.Builder("")
											.body(message)
											.badge(1)
											.clickAction(notificationType)
											.sound("default")
											.build();
	}

}
