package com.wedevol.iclass.core.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.service.StudentManagerService;
import com.wedevol.iclass.core.service.StudentService;

/**
 * Student Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/students")
public class StudentController {

	protected static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentManagerService stuMgrService;

	/********************* CRUD for student ****************************/

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Student> findAll() {
		return studentService.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Student findById(@PathVariable Long userId) {
		return studentService.findById(userId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@Valid @RequestBody Student student) {
		studentService.create(student);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long userId, @Valid @RequestBody Student student) {
		studentService.update(userId, student);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long userId) {
		studentService.delete(userId);
	}

	/************** Courses & Students & Enrollment *************/

	@RequestMapping(value = "/{userId}/courses", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Course> findCoursesByStudentIdWithCourseStatusFilter(@PathVariable Long userId,
			@RequestParam(value = "status", defaultValue = "free,payed") String courseStatusFilter) {
		logger.info("Find courses of a student filtered by the supplied course status: " + courseStatusFilter);
		return stuMgrService.findCoursesByStudentIdWithCourseStatusFilter(userId, courseStatusFilter);
	}
}
