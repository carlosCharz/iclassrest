package com.wedevol.iclass.core.job;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.wedevol.iclass.core.entity.BatchNotification;
import com.wedevol.iclass.core.service.BatchNotificationService;
import com.wedevol.iclass.core.service.NotificationService;

/**
 * Batch process job
 *
 * @author Charz++
 */
@DisallowConcurrentExecution
public class BatchProcessJob extends QuartzJobBean {

	protected static final Logger logger = LoggerFactory.getLogger(BatchProcessJob.class);

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private BatchNotificationService batchNotificationService;
	
	@Override
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// To execute on demand
		this.execute();
	}

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
