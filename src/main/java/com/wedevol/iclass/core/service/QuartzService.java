package com.wedevol.iclass.core.service;

import com.wedevol.iclass.core.util.DefaultInterface;

/**
 * Quartz Service Interface
 * 
 * @author charz
 *
 */
public interface QuartzService extends DefaultInterface {

	void runQuartz(String jobGroup, String jobName);
	
}
