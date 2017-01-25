package com.wedevol.iclass.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wedevol.iclass.core.entity.Course;
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
	public void sendWelcomeNotificationToStudent(String tokenTo) {
		// TODO: send notification to the admins
		final NotificationType notificationType = NotificationType.WELCOME_STUDENT;
		final String message = MessageContentBuilder.buildMessageContent(notificationType, new ArrayList<>());
		final NotificationRequest request = new NotificationRequest(message, notificationType);
		sendNotificationInThread(request, tokenTo);
	}

	@Override
	public void sendWelcomeNotificationToInstructor(String tokenTo) {
		// TODO: send notification to the admins
		final NotificationType notificationType = NotificationType.WELCOME_INSTRUCTOR;
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

	@Override
	public void sendNewClassRequestNotificationToInstructor(String tokenTo, Student student, Course course) {
		// TODO: send notification to the admins
		final NotificationType notificationType = NotificationType.NEW_CLASS_REQUEST_FOR_INSTRUCTOR;
		final List<String> data = new ArrayList<String>();
		data.add(student.getFullName());
		data.add(course.getName());
		final String message = MessageContentBuilder.buildMessageContent(notificationType, data);
		final NotificationRequest request = new NotificationRequest(message, notificationType);
		sendNotificationInThread(request, tokenTo);
	}

	@Override
	public void sendClassConfirmedNotificationToStudent(String tokenTo, Instructor instructor, Course course) {
		// TODO: send notification to the admins
		final NotificationType notificationType = NotificationType.CLASS_CONFIRMED_FOR_STUDENT;
		final List<String> data = new ArrayList<String>();
		data.add(instructor.getFullName());
		data.add(course.getName());
		final String message = MessageContentBuilder.buildMessageContent(notificationType, data);
		final NotificationRequest request = new NotificationRequest(message, notificationType);
		sendNotificationInThread(request, tokenTo);
	}

	@Override
	public void sendClassRejectedNotificationToStudent(String tokenTo, Instructor instructor, Course course) {
		// TODO: send notification to the admins
		final NotificationType notificationType = NotificationType.CLASS_REJECTED_FOR_STUDENT;
		final List<String> data = new ArrayList<String>();
		data.add(instructor.getFullName());
		data.add(course.getName());
		final String message = MessageContentBuilder.buildMessageContent(notificationType, data);
		final NotificationRequest request = new NotificationRequest(message, notificationType);
		sendNotificationInThread(request, tokenTo);
	}

	@Override
	public void sendCourseApprovedNotificationToInstructor(String tokenTo, Course course) {
		// TODO: send notification to the admins
		final NotificationType notificationType = NotificationType.NEW_COURSE_APPROVED_FOR_INSTRUCTOR;
		final List<String> data = new ArrayList<String>();
		data.add(course.getName());
		final String message = MessageContentBuilder.buildMessageContent(notificationType, data);
		final NotificationRequest request = new NotificationRequest(message, notificationType);
		sendNotificationInThread(request, tokenTo);
	}

}
