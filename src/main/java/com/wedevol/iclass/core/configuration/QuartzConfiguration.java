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
		stFactory.setName("fiestaTrigger");
		stFactory.setGroup("fiestaGroup");
		stFactory.setCronExpression("0 59 23 ? * *");// Job is scheduled every day at 23:59pm
		//stFactory.setCronExpression("0/5 * * 1/1 * ? *");// Job is scheduled after every 10 seconds
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
