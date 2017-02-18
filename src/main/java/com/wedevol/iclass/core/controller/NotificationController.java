package com.wedevol.iclass.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wedevol.iclass.core.annotation.Authorize;
import com.wedevol.iclass.core.service.NotificationService;

/**
 * Notification Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/notification")
public class NotificationController {

	protected static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	private NotificationService notificationService;

	@Authorize(basic = true)
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void sendDirectNotificationToToken(@RequestParam(value = "message", required = true) String message,
			@RequestParam(value = "token", required = true) String token) {
		logger.info("Sending the message '{}' to the token '{}'", message, token);
		notificationService.sendDirectNotificationToToken(token, message);
	}
	
	@Authorize(basic = true)
	@RequestMapping(value = "/send/student", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void sendDirectNotificationToStudent(@RequestParam(value = "message", required = true) String message,
			@RequestParam(value = "id", required = true) Long id) {
		logger.info("Sending the message '{}' to the student id '{}'", message, id);
		notificationService.sendDirectNotificationToStudent(id, message);
	}
	
	@Authorize(basic = true)
	@RequestMapping(value = "/send/instructor", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void sendDirectNotificationToInstructor(@RequestParam(value = "message", required = true) String message,
			@RequestParam(value = "id", required = true) Long id) {
		logger.info("Sending the message '{}' to the instructor id '{}'", message, id);
		notificationService.sendDirectNotificationToInstructor(id, message);
	}

}
