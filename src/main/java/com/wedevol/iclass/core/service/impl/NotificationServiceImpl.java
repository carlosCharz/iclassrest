package com.wedevol.iclass.core.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wedevol.iclass.core.notifier.FCMMessageSender;
import com.wedevol.iclass.core.notifier.MessageContentBuilder;
import com.wedevol.iclass.core.notifier.NotificationRequest;
import com.wedevol.iclass.core.notifier.NotificationType;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.NotificationService;
import com.wedevol.iclass.core.service.StudentService;

/**
 * Notification Service Implementation
 * 
 * @author charz
 *
 */
@Service
public class NotificationServiceImpl implements NotificationService {

	protected static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	private FCMMessageSender fcmMessageSender;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private StudentService studentService;

	@Override
	public void sendStudentWelcomeNotification(String tokenTo, NotificationType notificationType) {
		// TODO: send notification to the admins
		final String message = MessageContentBuilder.buildMessageContent(notificationType, new ArrayList<>());
		final NotificationRequest request = new NotificationRequest(message, notificationType);
		fcmMessageSender.send(request, tokenTo);
	}

	@Override
	public void sendInstructorWelcomeNotification(String tokenTo, NotificationType notificationType) {
		// TODO: send notification to the admins
		final String message = MessageContentBuilder.buildMessageContent(notificationType, new ArrayList<>());
		final NotificationRequest request = new NotificationRequest(message, notificationType);
		fcmMessageSender.send(request, tokenTo);
	}

}
