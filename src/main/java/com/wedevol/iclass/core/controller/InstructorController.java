package com.wedevol.iclass.core.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.wedevol.iclass.core.entity.InstructorBasic;
import com.wedevol.iclass.core.service.InstructorManagerService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.util.CommonUtil;

/**
 * Instructor Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/instructors")
public class InstructorController {

	protected static final Logger logger = LoggerFactory.getLogger(InstructorController.class);

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private InstructorManagerService insMgrService;

	/********************* CRUD for instructor ****************************/

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Instructor> findAll() {
		return instructorService.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Instructor findById(@PathVariable Long userId) {
		return instructorService.findById(userId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@Valid @RequestBody Instructor instructor) {
		instructorService.create(instructor);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long userId, @Valid @RequestBody Instructor instructor) {
		instructorService.update(userId, instructor);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long userId) {
		instructorService.delete(userId);
	}

	/********* Courses & Instructors & Enrollment *************/

	@RequestMapping(value = "/{userId}/courses", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Course> findCoursesByInstructorId(@PathVariable Long userId,
			@RequestParam(value = "status", defaultValue = "free,payed") String statusFilter) {
		logger.info("Find courses of an instructor filtered by the supplied course status: " + statusFilter);
		return insMgrService.findCoursesByInstructorId(userId, statusFilter);
	}

	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<InstructorBasic> findInstructorsByCourseIdByDateTime(@RequestParam("courseId") Long courseId,
			@RequestParam("classDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date classDate,
			@RequestParam("startTime") Integer startTime, @RequestParam("endTime") Integer endTime) {
		logger.info("Find instructors of the course " + courseId + " in " + CommonUtil.dateToString(classDate)
				+ " from " + startTime + " to " + endTime);
		return insMgrService.findInstructorsByCourseIdByDateTime(courseId, classDate, startTime, endTime);
	}
}
