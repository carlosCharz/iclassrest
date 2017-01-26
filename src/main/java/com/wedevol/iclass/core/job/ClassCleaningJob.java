package com.wedevol.iclass.core.job;

import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

	public void execute() {
		logger.info("Class cleaning job executed");
	}

}
