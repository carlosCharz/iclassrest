package com.wedevol.iclass.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.wedevol.iclass.core.annotation.Authorize;
import com.wedevol.iclass.core.service.QuartzService;

/**
 * Quartz Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController {

	protected static final Logger logger = LoggerFactory.getLogger(QuartzController.class);

	@Autowired
	private QuartzService quartzService;

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/groups/{jobGroup}/jobs/{jobName}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void runQuartzOnDemand(@PathVariable String jobGroup, @PathVariable String jobName) {
		quartzService.runQuartz(jobGroup, jobName);
	}

}
