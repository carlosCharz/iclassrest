package com.wedevol.iclass.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.wedevol.iclass.core.job.BatchProcessJob;

/**
 * Quartz configuration file
 *
 * @author charz
 */
@Configuration
public class QuartzConfiguration {

	protected static final Logger logger = LoggerFactory.getLogger(QuartzConfiguration.class);

	@Bean
	public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
		MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
		obj.setTargetBeanName("batchProcessJob");
		obj.setTargetMethod("execute");
		return obj;
	}

	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean() {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("batchTrigger");
		stFactory.setGroup("batchGroup");
		stFactory.setCronExpression("0 0/45 * * * ? *");// Job is scheduled after every 45 minutes
		logger.info("Setting up the batch process job to be every 45 minutes");
		return stFactory;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(cronTriggerFactoryBean().getObject());
		return scheduler;
	}

	@Bean(name = "batchProcessJob")
	public BatchProcessJob batchProcessJobBean() {
		return new BatchProcessJob();
	}
}
