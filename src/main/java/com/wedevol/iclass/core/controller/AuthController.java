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
import com.wedevol.iclass.core.view.request.LoginView;
import com.wedevol.iclass.core.view.response.AdminView;
import com.wedevol.iclass.core.view.response.InstructorView;
import com.wedevol.iclass.core.view.response.StudentView;

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
	public StudentView loginStudent(@Valid @RequestBody LoginView request) {
		return authService.loginStudent(request.getEmail(), request.getPassword());
	}

	@RequestMapping(value = "/instructor/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public InstructorView loginInstructor(@Valid @RequestBody LoginView request) {
		return authService.loginInstructor(request.getEmail(), request.getPassword());
	}

	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AdminView loginAdmin(@Valid @RequestBody LoginView request) {
		return authService.loginAdmin(request.getEmail(), request.getPassword());
	}

}
