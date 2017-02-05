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
 * Batch process job
 *
 * @author Charz++
 */
@DisallowConcurrentExecution
public class BatchProcessJob {

	protected static final Logger logger = LoggerFactory.getLogger(BatchProcessJob.class);

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private BatchNotificationService batchNotificationService;

	public void execute() {
		logger.info("****** Batch process job executed ******");
		processNotificationsToBeSent();
	}

	private void processNotificationsToBeSent() {
		final List<BatchNotification> batchList = batchNotificationService.getNotificationsToBeSent();
		notificationService.sendBatchNotifications(batchList);
		batchList.forEach(batch -> {
			batchNotificationService.delete(batch.getId());
		});
	}

}
