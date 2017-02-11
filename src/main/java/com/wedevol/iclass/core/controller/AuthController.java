package com.wedevol.iclass.core.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wedevol.iclass.core.service.AuthService;
import com.wedevol.iclass.core.view.request.FCMTokenView;
import com.wedevol.iclass.core.view.request.LoginView;
import com.wedevol.iclass.core.view.response.AdminFull;
import com.wedevol.iclass.core.view.response.InstructorFull;
import com.wedevol.iclass.core.view.response.StudentFull;

/**
 * Auth Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	protected static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthService authService;

	/********************* Authentication logic ****************************/

	@RequestMapping(value = "/student/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public StudentFull loginStudent(@Valid @RequestBody LoginView request) {
		return authService.loginStudent(request.getEmail(), request.getPassword());
	}

	@RequestMapping(value = "/instructor/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public InstructorFull loginInstructor(@Valid @RequestBody LoginView request) {
		return authService.loginInstructor(request.getEmail(), request.getPassword());
	}

	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AdminFull loginAdmin(@Valid @RequestBody LoginView request) {
		return authService.loginAdmin(request.getEmail(), request.getPassword());
	}
	
	@RequestMapping(value = "/token/refresh", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void refreshFCMToken(@Valid @RequestBody FCMTokenView tokenView) {
		authService.refreshFCMToken(tokenView.getFcmToken(), tokenView.getDeviceId());
	}

}
