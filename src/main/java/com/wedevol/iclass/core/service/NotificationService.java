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

}
