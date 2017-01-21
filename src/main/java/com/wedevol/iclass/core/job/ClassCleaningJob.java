package com.wedevol.iclass.core.job;

import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class cleaning job
 *
 * @author Charz++
 */
@DisallowConcurrentExecution
public class ClassCleaningJob {

	protected static final Logger logger = LoggerFactory.getLogger(ClassCleaningJob.class);

	public void execute() {
		logger.info("Class cleaning job executed");
	}

}
