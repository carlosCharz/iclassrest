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

import springfox.documentation.annotations.ApiIgnore;

import com.wedevol.iclass.core.annotation.Authorize;
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

	@Autowired
	private InstructorEnrollmentService insEnrService;

	/************** CRUD for instructor enrollment **********************/

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/instructorenrollments", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<InstructorEnrollment> findAll() {
		return insEnrService.findAll();
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/instructors/{userId}/courses/{courseId}/enrollment", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public InstructorEnrollment findById(@PathVariable Long userId, @PathVariable Long courseId) {
		final InstructorEnrollmentId id = new InstructorEnrollmentId(userId, courseId);
		return insEnrService.findById(id);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/instructorenrollments", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public InstructorEnrollment create(@Valid @RequestBody InstructorEnrollment instructorCourse) {
		return insEnrService.create(instructorCourse);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/instructors/{userId}/courses/{courseId}/enrollment", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long userId, @PathVariable Long courseId,
			@Valid @RequestBody InstructorEnrollment instructorCourse) {
		final InstructorEnrollmentId id = new InstructorEnrollmentId(userId, courseId);
		insEnrService.update(id, instructorCourse);
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/instructors/{userId}/courses/{courseId}/enrollment", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long userId, @PathVariable Long courseId) {
		final InstructorEnrollmentId id = new InstructorEnrollmentId(userId, courseId);
		insEnrService.delete(id);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/instructors/{userId}/courses/{courseId}/enrollment/approve", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void approveCourseInstructorEnrollment(@PathVariable Long userId, @PathVariable Long courseId) {
		insEnrService.approveCourseInstructorEnrollment(userId, courseId);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/instructors/{userId}/courses/{courseId}/enrollment/deny", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void denyCourseInstructorEnrollment(@PathVariable Long userId, @PathVariable Long courseId) {
		insEnrService.denyCourseInstructorEnrollment(userId, courseId);
	}
}
