package com.wedevol.iclass.core.service;


/**
 * Notification Service Interface
 * 
 * @author charz
 *
 */
public interface NotificationService {

	void sendStudentWelcomeNotification(String tokenTo);

	void sendInstructorWelcomeNotification(String tokenTo);

	void sendDirectNotificationToToken(String tokenTo, String message);
	
	void sendDirectNotificationToStudent(Long studentId , String message);
	
	void sendDirectNotificationToInstructor(Long instructorId , String message);

}
