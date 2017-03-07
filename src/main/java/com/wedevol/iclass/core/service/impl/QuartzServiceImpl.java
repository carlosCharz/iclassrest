package com.wedevol.iclass.core.service.impl;

import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.service.QuartzService;

/**
 * Quartz Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class QuartzServiceImpl implements QuartzService {

	protected static final Logger logger = LoggerFactory.getLogger(QuartzServiceImpl.class);

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Override
	public void runQuartz(String jobGroup, String jobName) {
		JobKey jobKey = new JobKey(jobName, jobGroup);
		try {
			schedulerFactoryBean.getScheduler()
								.triggerJob(jobKey);
		} catch (SchedulerException e) {
			logger.error(String.format("Couldn't execute the job: %s", jobName), e);
		}
	}

}
