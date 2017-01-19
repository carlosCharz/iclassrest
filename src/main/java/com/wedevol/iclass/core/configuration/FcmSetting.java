package com.wedevol.iclass.core.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * FCM settings loaded from a property file
 * 
 * @author Charz++
 */
@Component
@ConfigurationProperties(prefix = "fcm")
public class FcmSetting {

	protected static final Logger logger = LoggerFactory.getLogger(FcmSetting.class);

	private String serverKey;

	private Integer timeToLive;

	private String collapseKey;

	private Integer retries;

	public FcmSetting() {
		logger.info("Loading FCM properties");
	}

	@PostConstruct
	public void postConstruct() {
		logger.info("FCM properties -> serverKey: '{}', timeToLive: '{}', collapseKey: '{}', retries: '{}'", serverKey,
				timeToLive, collapseKey, retries);
	}

	public String getServerKey() {
		return serverKey;
	}

	public void setServerKey(String serverKey) {
		this.serverKey = serverKey;
	}

	public Integer getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
	}

	public String getCollapseKey() {
		return collapseKey;
	}

	public void setCollapseKey(String collapseKey) {
		this.collapseKey = collapseKey;
	}

	public Integer getRetries() {
		return retries;
	}

	public void setRetries(Integer retries) {
		this.retries = retries;
	}

}
