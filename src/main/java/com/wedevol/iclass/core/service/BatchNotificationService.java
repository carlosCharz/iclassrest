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
public interface BatchNotificationService {

	List<BatchNotification> findAll();

	BatchNotification findById(Long batchId);

	BatchNotification create(BatchNotification batch);

	void update(Long batchId, BatchNotification batch);

	void delete(Long batchId);
	
	List<BatchNotification> getNotificationsToBeSent();
	
	void saveClassComingSoonReminder(Student student, Instructor instructor, Course course, Clase clase);
	
	void deleteClassComingSoonReminder(Long classId);
	
}
