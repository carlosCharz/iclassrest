package com.wedevol.iclass.core.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Environment settings loaded from a property file
 * 
 * @author Charz++
 */
@Component
@ConfigurationProperties(prefix = "env")
public class EnvironmentSetting {

	protected static final Logger logger = LoggerFactory.getLogger(EnvironmentSetting.class);

	private String stack;

	public EnvironmentSetting() {
		logger.info("Loading Environment properties");
	}

	@PostConstruct
	public void postConstruct() {
		logger.info("Environment properties -> stack: '{}'", stack);
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

}
