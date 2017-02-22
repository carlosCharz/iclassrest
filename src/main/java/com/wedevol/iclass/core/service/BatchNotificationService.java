package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.BatchNotification;
import com.wedevol.iclass.core.entity.Clase;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;

/**
 * Batch Notification Service Interface
 * 
 * @author charz
 *
 */
public interface BatchNotificationService extends BaseService<BatchNotification>{

	List<BatchNotification> findAll();
	
	List<BatchNotification> getNotificationsToBeSent();
	
	void saveClassComingSoonReminder(Student student, Instructor instructor, Course course, Clase clase);
	
	void deleteClassComingSoonReminder(Long classId);
	
}
