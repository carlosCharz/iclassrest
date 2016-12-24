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
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorEnrollment;
import com.wedevol.iclass.core.entity.InstructorEnrollmentId;
import com.wedevol.iclass.core.service.InstructorEnrollmentService;

/**
 * Instructor Enrollment Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("")
public class InstructorEnrollmentController {

	protected static final Logger logger = LoggerFactory.getLogger(InstructorEnrollmentController.class);

	@Autowired
	private InstructorEnrollmentService instructorEnrollmentService;

	/************** Courses & Instructors **********************/
	@RequestMapping(value = "/instructors/{instructorId}/courses", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Course> findCourses(@PathVariable Long instructorId,
			@RequestParam(value = "status", defaultValue = "free,payed") String statusFilter) {
		logger.info(
				"Controller -> find courses of an instructor filtered by the supplied course status: " + statusFilter);
		return instructorEnrollmentService.findCourses(instructorId, statusFilter);
	}

	@RequestMapping(value = "/courses/{courseId}/instructors", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Instructor> findInstructors(@PathVariable Long courseId) {
		logger.info("Controller -> find instructors of a course");
		return instructorEnrollmentService.findInstructors(courseId);
	}

	/************** CRUD for instructor enrollment **********************/
	@RequestMapping(value = "/instructorenrollments", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<InstructorEnrollment> findAll() {
		logger.info("Controller -> find all");
		return instructorEnrollmentService.findAll();
	}

	@RequestMapping(value = "/instructors/{instructorId}/courses/{courseId}/enrollment", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public InstructorEnrollment findById(@PathVariable Long instructorId, @PathVariable Long courseId) {
		logger.info("Controller -> find by id");
		final InstructorEnrollmentId id = new InstructorEnrollmentId(instructorId, courseId);
		return instructorEnrollmentService.findById(id);
	}

	@RequestMapping(value = "/instructorenrollments", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@Valid @RequestBody InstructorEnrollment instructorCourse) {
		logger.info("Controller -> create");
		instructorEnrollmentService.create(instructorCourse);
	}

	@RequestMapping(value = "/instructors/{instructorId}/courses/{courseId}/enrollment", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long instructorId, @PathVariable Long courseId,
			@Valid @RequestBody InstructorEnrollment instructorCourse) {
		logger.info("Controller -> update");
		final InstructorEnrollmentId id = new InstructorEnrollmentId(instructorId, courseId);
		instructorEnrollmentService.update(id, instructorCourse);
	}

	@RequestMapping(value = "/instructors/{instructorId}/courses/{courseId}/enrollment", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long instructorId, @PathVariable Long courseId) {
		logger.info("Controller -> delete");
		final InstructorEnrollmentId id = new InstructorEnrollmentId(instructorId, courseId);
		instructorEnrollmentService.delete(id);
	}
}
