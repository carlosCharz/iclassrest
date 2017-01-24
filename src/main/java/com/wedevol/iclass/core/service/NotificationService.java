package com.wedevol.iclass.core.service;

import com.wedevol.iclass.core.notifier.NotificationType;

/**
 * Notification Service Interface
 * 
 * @author charz
 *
 */
public interface NotificationService {

	void sendStudentWelcomeNotification(String tokenTo, NotificationType notificationType);

	void sendInstructorWelcomeNotification(String tokenTo, NotificationType notificationType);

	void sendDirectNotificationToToken(String tokenTo, String message);
	
	void sendDirectNotificationToStudent(Long studentId , String message);
	
	void sendDirectNotificationToInstructor(Long instructorId , String message);

}
