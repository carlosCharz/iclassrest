package com.wedevol.iclass.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Greeting;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.repository.StudentRepository;

/**
 * Student Controller
 */
@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Student> findAll() {

		final Iterable<Student> studentsIterator = studentRepository.findAll();
		List<Student> result = Lists.newArrayList(studentsIterator);
		return result;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Greeting findOne() {
		Greeting greet1 = new Greeting(1, "Carlos");
		return greet1;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create() {
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update() {
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete() {
	}
}
