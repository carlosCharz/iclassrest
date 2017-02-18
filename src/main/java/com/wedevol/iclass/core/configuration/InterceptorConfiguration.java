package com.wedevol.iclass.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wedevol.iclass.core.interceptor.AuthorizationInterceptor;

/**
 * Request interceptor configuration
 *
 * @author Charz++
 */
@EnableWebMvc  
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public HandlerInterceptor authorizationInterceptor() {
	    return new AuthorizationInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authorizationInterceptor());
	}
}
