package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.BatchNotification;

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
	
}
