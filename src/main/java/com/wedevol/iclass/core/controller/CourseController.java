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

import com.wedevol.iclass.core.annotation.Authorize;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Material;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.service.CourseService;

import springfox.documentation.annotations.ApiIgnore;

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

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Course> findAll() {
		return courseService.findAll();
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Course findById(@PathVariable Long courseId) {
		return courseService.findById(courseId);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Course create(@Valid @RequestBody Course course) {
		return courseService.create(course);
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/{courseId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long courseId, @Valid @RequestBody Course course) {
		courseService.update(courseId, course);
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/{courseId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long courseId) {
		courseService.delete(courseId);
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/{courseId}/instructors", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Instructor> findInstructorsByCourseId(@PathVariable Long courseId) {
		return courseService.findInstructorsByCourseId(courseId);
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/{courseId}/students", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Student> findStudentsByCourseId(@PathVariable Long courseId) {
		return courseService.findStudentsByCourseId(courseId);
	}
	
	@Authorize(basic = true)
	@RequestMapping(value = "/{courseId}/materials", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Material> findMaterialsByCourseId(@PathVariable Long courseId) {
		return courseService.findMaterialsByCourseId(courseId);
	}
}
