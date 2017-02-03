package com.wedevol.iclass.core.job;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wedevol.iclass.core.entity.BatchNotification;
import com.wedevol.iclass.core.service.BatchNotificationService;
import com.wedevol.iclass.core.service.NotificationService;

/**
 * Class cleaning job
 *
 * @author Charz++
 */
@DisallowConcurrentExecution
public class ClassCleaningJob {

	protected static final Logger logger = LoggerFactory.getLogger(ClassCleaningJob.class);
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private BatchNotificationService batchNotificationService;

	public void execute() {
		logger.info("Class cleaning job executed");
		final List<BatchNotification> batchList = batchNotificationService.getNotificationsToBeSent();
		batchList.forEach(batch -> notificationService.send);
		
	}

}
