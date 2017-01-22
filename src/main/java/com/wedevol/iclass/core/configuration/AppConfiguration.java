package com.wedevol.iclass.core.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Application configuration class
 *
 * @author Charz++
 */
@EnableConfigurationProperties({ FcmSetting.class, EnvironmentSetting.class })
public class AppConfiguration {

}
