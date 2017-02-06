package com.wedevol.iclass.core.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Amazon S3 settings loaded from a property file
 * 
 * @author Charz++
 */
@Component
@ConfigurationProperties(prefix = "amazons3")
public class AmazonS3Setting {

	protected static final Logger logger = LoggerFactory.getLogger(AmazonS3Setting.class);

	private String accessKey;
	private String secretKey;
	private String bucket;
	private String prefix;

	public AmazonS3Setting() {
		logger.info("Loading Amazon S3 properties");
	}

	@PostConstruct
	public void postConstruct() {
		logger.info("Amazon S3 properties -> accessKey: '{}', secretKey: '{}', bucket: '{}', prefix: '{}'", accessKey,
				secretKey, bucket, prefix);
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
