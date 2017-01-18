package com.wedevol.iclass.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.wedevol.iclass.core.job.ClassCleaningJob;

/**
 * Quartz configuration file
 *
 * @author charz
 */
@Configuration
public class QuartzConfiguration {
	@Bean
	public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
		MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
		obj.setTargetBeanName("classCleaningJob");
		obj.setTargetMethod("execute");
		return obj;
	}

	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean() {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("classTrigger");
		stFactory.setGroup("classGroup");
		stFactory.setCronExpression("0 0/1 * * * ? *");// Job is scheduled after every 1 minute
		return stFactory;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(cronTriggerFactoryBean().getObject());
		return scheduler;
	}

	@Bean(name = "classCleaningJob")
	public ClassCleaningJob classCleaningJobBean() {
		return new ClassCleaningJob();
	}
}
