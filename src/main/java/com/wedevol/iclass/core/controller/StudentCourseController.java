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

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.StudentCourse;
import com.wedevol.iclass.core.entity.StudentCourseId;
import com.wedevol.iclass.core.service.StudentCourseService;

/**
 * Student_Course Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("")
public class StudentCourseController {

	protected static final Logger logger = LoggerFactory.getLogger(StudentCourseController.class);

	@Autowired
	private StudentCourseService studentCourseService;

	/************** Courses & Students **********************/
	@RequestMapping(value = "/students/{studentId}/courses", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Course> findCourses(@PathVariable Long studentId) {
		logger.info("Controller -> find courses of a student");
		return studentCourseService.findCourses(studentId);
	}

	@RequestMapping(value = "/courses/{courseId}/students", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Student> findStudents(@PathVariable Long courseId) {
		logger.info("Controller -> find students of a course");
		return studentCourseService.findStudents(courseId);
	}

	/************** CRUD for student_course **********************/
	@RequestMapping(value = "/studentcourse", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<StudentCourse> findAll() {
		logger.info("Controller -> find all");
		return studentCourseService.findAll();
	}

	@RequestMapping(value = "/studentcourse/{studentId}/{courseId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public StudentCourse findById(@PathVariable Long studentId, @PathVariable Long courseId) {
		logger.info("Controller -> find by id");
		final StudentCourseId id = new StudentCourseId(studentId, courseId);
		return studentCourseService.findById(id);
	}

	@RequestMapping(value = "/studentcourse", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@Valid @RequestBody StudentCourse studentCourse) {
		logger.info("Controller -> create");
		studentCourseService.create(studentCourse);
	}

	@RequestMapping(value = "/studentcourse/{studentId}/{courseId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long studentId, @PathVariable Long courseId,
			@Valid @RequestBody StudentCourse studentCourse) {
		logger.info("Controller -> update");
		final StudentCourseId id = new StudentCourseId(studentId, courseId);
		studentCourseService.update(id, studentCourse);
	}

	@RequestMapping(value = "/studentcourse/{studentId}/{courseId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long studentId, @PathVariable Long courseId) {
		logger.info("Controller -> delete");
		final StudentCourseId id = new StudentCourseId(studentId, courseId);
		studentCourseService.delete(id);
	}
}
