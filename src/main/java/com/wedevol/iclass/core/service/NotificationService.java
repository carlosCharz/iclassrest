package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.BatchNotification;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;

/**
 * Notification Service Interface
 * 
 * @author charz
 *
 */
public interface NotificationService {

	void sendWelcomeNotificationToStudent(String tokenTo);

	void sendWelcomeNotificationToInstructor(String tokenTo);

	void sendDirectNotificationToToken(String tokenTo, String message);

	void sendDirectNotificationToStudent(Long studentId, String message);

	void sendDirectNotificationToInstructor(Long instructorId, String message);

	void sendNewClassRequestNotificationToInstructor(String tokenTo, Student student, Course course);

	void sendClassConfirmedNotificationToStudent(String tokenTo, Instructor instructor, Course course);

	void sendRateFinishedClassNotificationToStudent(String tokenTo, Instructor instructor, Course course);

	void sendCourseApprovedNotificationToInstructor(String tokenTo, Course course);
	
	void sendBatchNotifications(List<BatchNotification> batchs);
	
	void sendClassRejectedNotificationToStudent(String tokenTo, Instructor instructor, Course course);

}
