package com.wedevol.iclass.core.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;
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
	public void sendStudentWelcomeNotification(String tokenTo) {
		// TODO: send notification to the admins
		final NotificationType notificationType = NotificationType.WELCOME_STUDENT;
		final String message = MessageContentBuilder.buildMessageContent(notificationType, new ArrayList<>());
		final NotificationRequest request = new NotificationRequest(message, notificationType);
		sendNotificationInThread(request, tokenTo);
	}

	@Override
	public void sendInstructorWelcomeNotification(String tokenTo) {
		// TODO: send notification to the admins
		NotificationType notificationType = NotificationType.WELCOME_INSTRUCTOR;
		final String message = MessageContentBuilder.buildMessageContent(notificationType, new ArrayList<>());
		final NotificationRequest request = new NotificationRequest(message, notificationType);
		sendNotificationInThread(request, tokenTo);
	}

	@Override
	public void sendDirectNotificationToToken(String tokenTo, String message) {
		final NotificationRequest request = new NotificationRequest(message, NotificationType.DIRECT_MESSAGE);
		sendNotificationInThread(request, tokenTo);
	}

	@Override
	public void sendDirectNotificationToStudent(Long studentId, String message) {
		final Student student = studentService.findById(studentId);
		final NotificationRequest request = new NotificationRequest(message, NotificationType.DIRECT_MESSAGE);
		sendNotificationInThread(request, student.getFcmToken());
	}

	@Override
	public void sendDirectNotificationToInstructor(Long instructorId, String message) {
		final Instructor instructor = instructorService.findById(instructorId);
		final NotificationRequest request = new NotificationRequest(message, NotificationType.DIRECT_MESSAGE);
		sendNotificationInThread(request, instructor.getFcmToken());
	}

	/**
	 * @param notificationRequest
	 * @param tokenTo
	 */
	private void sendNotificationInThread(NotificationRequest request, String tokenTo) {
		Runnable runnable = () -> {
			fcmMessageSender.send(request, tokenTo);
		};
		new Thread(runnable).start();
	}

}