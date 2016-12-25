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
import com.wedevol.iclass.core.entity.StudentEnrollment;
import com.wedevol.iclass.core.entity.StudentEnrollmentId;
import com.wedevol.iclass.core.service.StudentEnrollmentService;

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
	private StudentEnrollmentService studentEnrollmentService;

	/************** Courses & Students **********************/
	@RequestMapping(value = "/students/{studentId}/courses", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Course> findCourses(@PathVariable Long studentId, @RequestParam(value="status", defaultValue="free,payed") String statusFilter) {
		logger.info("Find courses of a student filtered by the supplied course status: "+ statusFilter);
		return studentEnrollmentService.findCourses(studentId, statusFilter);
	}

	@RequestMapping(value = "/courses/{courseId}/students", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Student> findStudents(@PathVariable Long courseId) {
		return studentEnrollmentService.findStudents(courseId);
	}

	/************** CRUD for student enrollment **********************/
	@RequestMapping(value = "/studentenrollments", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<StudentEnrollment> findAll() {
		return studentEnrollmentService.findAll();
	}

	@RequestMapping(value = "/students/{studentId}/courses/{courseId}/enrollment", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public StudentEnrollment findById(@PathVariable Long studentId, @PathVariable Long courseId) {
		final StudentEnrollmentId id = new StudentEnrollmentId(studentId, courseId);
		return studentEnrollmentService.findById(id);
	}

	@RequestMapping(value = "/studentenrollments", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@Valid @RequestBody StudentEnrollment studentCourse) {
		studentEnrollmentService.create(studentCourse);
	}

	@RequestMapping(value = "/students/{studentId}/courses/{courseId}/enrollment", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long studentId, @PathVariable Long courseId,
			@Valid @RequestBody StudentEnrollment studentCourse) {
		final StudentEnrollmentId id = new StudentEnrollmentId(studentId, courseId);
		studentEnrollmentService.update(id, studentCourse);
	}

	@RequestMapping(value = "/students/{studentId}/courses/{courseId}/enrollment", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long studentId, @PathVariable Long courseId) {
		final StudentEnrollmentId id = new StudentEnrollmentId(studentId, courseId);
		studentEnrollmentService.delete(id);
	}
}