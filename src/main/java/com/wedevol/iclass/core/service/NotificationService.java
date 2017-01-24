package com.wedevol.iclass.core.service;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;


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
	
	void sendInstructorNewClassRequestNotification(String tokenTo, Student student, Course course);

}
