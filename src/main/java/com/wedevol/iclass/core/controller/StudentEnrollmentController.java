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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wedevol.iclass.core.entity.StudentEnrollment;
import com.wedevol.iclass.core.entity.StudentEnrollmentId;
import com.wedevol.iclass.core.service.StudentEnrollmentService;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Student Enrollment Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("")
public class StudentEnrollmentController {

	protected static final Logger logger = LoggerFactory.getLogger(StudentEnrollmentController.class);

	@Autowired
	private StudentEnrollmentService stuEnrService;

	/************** CRUD for student enrollment **********************/

	@ApiIgnore
	@RequestMapping(value = "/studentenrollments", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<StudentEnrollment> findAll() {
		return stuEnrService.findAll();
	}

	@ApiIgnore
	@RequestMapping(value = "/students/{userId}/courses/{courseId}/enrollment", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public StudentEnrollment findById(@PathVariable Long userId, @PathVariable Long courseId) {
		final StudentEnrollmentId id = new StudentEnrollmentId(userId, courseId);
		return stuEnrService.findById(id);
	}

	@RequestMapping(value = "/studentenrollments", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public StudentEnrollment create(@Valid @RequestBody StudentEnrollment studentCourse) {
		return stuEnrService.create(studentCourse);
	}

	@RequestMapping(value = "/students/{userId}/courses/{courseId}/enrollment", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long userId, @PathVariable Long courseId,
			@Valid @RequestBody StudentEnrollment studentCourse) {
		final StudentEnrollmentId id = new StudentEnrollmentId(userId, courseId);
		stuEnrService.update(id, studentCourse);
	}

	@ApiIgnore
	@RequestMapping(value = "/students/{userId}/courses/{courseId}/enrollment", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long userId, @PathVariable Long courseId) {
		final StudentEnrollmentId id = new StudentEnrollmentId(userId, courseId);
		stuEnrService.delete(id);
	}
}
