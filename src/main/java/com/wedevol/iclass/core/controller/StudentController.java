package com.wedevol.iclass.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wedevol.iclass.core.entity.Greeting;

/**
 * Student Controller
 */
@RestController
@RequestMapping("/students")
public class StudentController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Greeting> findAll() {
		Greeting greet1 = new Greeting(1, "Carlos");
		Greeting greet2 = new Greeting(2, "Luis");
		List<Greeting> greetings = new ArrayList<>();
		greetings.add(greet1);
		greetings.add(greet2);
		return greetings;
	}
}
