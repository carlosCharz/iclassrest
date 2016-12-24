package com.wedevol.iclass.core.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.service.CourseService;

/**
 * Course Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	/********************* CRUD for course ****************************/
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Course> findAll() {
		return courseService.findAll();
	}

	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Course findById(@PathVariable Long courseId) {
		return courseService.findById(courseId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@Valid @RequestBody Course course) {
		courseService.create(course);
	}

	@RequestMapping(value = "/{courseId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long courseId, @Valid @RequestBody Course course) {
		courseService.update(courseId, course);
	}

	@RequestMapping(value = "/{courseId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long courseId) {
		courseService.delete(courseId);
	}
}
