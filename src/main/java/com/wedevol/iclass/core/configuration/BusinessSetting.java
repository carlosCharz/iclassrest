package com.wedevol.iclass.core.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Business settings loaded from a property file
 * 
 * @author Charz++
 */
@Component
@ConfigurationProperties(prefix = "business")
public class BusinessSetting {

	protected static final Logger logger = LoggerFactory.getLogger(BusinessSetting.class);

	private Float instructorDefaultPrice;

	private String instructorDefaultCurrency;
	
	private Integer levelBase;

	public BusinessSetting() {
		logger.info("Loading Business properties");
	}

	@PostConstruct
	public void postConstruct() {
		logger.info("Business properties -> instructorDefaultPrice: '{}', instructorDefaultCurrency: '{}', levelBase: '{}'",
				instructorDefaultPrice, instructorDefaultCurrency, levelBase);
	}

	public Float getInstructorDefaultPrice() {
		return instructorDefaultPrice;
	}

	public void setInstructorDefaultPrice(Float instructorDefaultPrice) {
		this.instructorDefaultPrice = instructorDefaultPrice;
	}

	public String getInstructorDefaultCurrency() {
		return instructorDefaultCurrency;
	}

	public void setInstructorDefaultCurrency(String instructorDefaultCurrency) {
		this.instructorDefaultCurrency = instructorDefaultCurrency;
	}

	public Integer getLevelBase() {
		return levelBase;
	}

	public void setLevelBase(Integer levelBase) {
		this.levelBase = levelBase;
	}

}
