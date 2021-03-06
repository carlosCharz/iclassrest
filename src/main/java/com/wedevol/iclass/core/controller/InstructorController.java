package com.wedevol.iclass.core.controller;

import static com.wedevol.iclass.core.util.CommonUtil.dateToString;

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

import springfox.documentation.annotations.ApiIgnore;

import com.wedevol.iclass.core.annotation.Authorize;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.view.request.UserView;
import com.wedevol.iclass.core.view.response.ClassResponse;
import com.wedevol.iclass.core.view.response.CourseResponse;
import com.wedevol.iclass.core.view.response.InstructorBasic;

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

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Instructor> findAll() {
		return instructorService.findAll();
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Instructor findById(@PathVariable Long userId) {
		return instructorService.findById(userId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Instructor create(@Valid @RequestBody UserView instructorView) {
		return instructorService.createInstructorWithCourse(instructorView);
	}

	@Authorize(hard = true)
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long userId, @Valid @RequestBody Instructor instructor) {
		instructorService.update(userId, instructor);
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long userId) {
		instructorService.delete(userId);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/{userId}/inactive", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void setUserInactive(@PathVariable Long userId) {
		instructorService.setUserInactive(userId);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/{userId}/courses", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CourseResponse> findCoursesByInstructorIdWithCourseStatusFilter(@PathVariable Long userId,
			@RequestParam(value = "status", defaultValue = "payed") String courseStatusFilter) {
		logger.info("Find courses of the instructor " + userId + " filtered by the supplied course status: "
				+ courseStatusFilter);
		return instructorService.findCoursesByInstructorIdWithCourseStatusFilter(userId, courseStatusFilter);
	}

	@ApiIgnore
	@Deprecated
	@Authorize(basic = true)
	@RequestMapping(value = "/fetch2", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<InstructorBasic> findInstructorsByCourseIdByDateTime(
			@RequestParam(value = "courseId", required = true) Long courseId,
			@RequestParam(value = "classDate", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date classDate,
			@RequestParam(value = "startTime", required = true) Integer startTime,
			@RequestParam(value = "endTime", required = true) Integer endTime) {
		logger.info("Find instructors of the course " + courseId + " in " + dateToString(classDate) + " from "
				+ startTime + " to " + endTime);
		return instructorService.findInstructorsByCourseIdByDateTime(courseId, classDate, startTime, endTime);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<InstructorBasic> findInstructorsByCourseIdByWeekDayByTime(
			@RequestParam(value = "courseId", required = true) Long courseId,
			@RequestParam(value = "weekDay", required = true) String weekDay,
			@RequestParam(value = "startTime", required = true) Integer startTime,
			@RequestParam(value = "endTime", required = true) Integer endTime) {
		logger.info("Find instructors of the course " + courseId + " for the week day '" + weekDay + "' from "
				+ startTime + " to " + endTime);
		return instructorService.findInstructorsByCourseIdByWeekDayByTime(courseId, weekDay, startTime, endTime);
	}

	@Authorize(hard = true)
	@RequestMapping(value = "/{userId}/classes/coming", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassResponse> findComingClassesByInstructorIdByDateTimeWithClassStatusFilter(@PathVariable Long userId,
			@RequestParam(value = "actualDate", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date actualDate,
			@RequestParam(value = "actualTime", required = true) Integer actualTime,
			@RequestParam(value = "status", defaultValue = "requested,confirmed") String statusFilter) {
		logger.info("Find coming classes of an instructor since " + actualTime + " hours " + dateToString(actualDate)
				+ " filtered by the supplied class status: " + statusFilter);
		return instructorService.findComingClassesByInstructorIdByDateTimeWithClassStatusFilter(userId, actualDate,
				actualTime, statusFilter);
	}

	@Authorize(hard = true)
	@RequestMapping(value = "/{userId}/classes/historic", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassResponse> findHistoricClassesByInstructorIdWithClassStatusFilter(@PathVariable Long userId,
			@RequestParam(value = "status", defaultValue = "requested,confirmed") String statusFilter) {
		logger.info("Find historic classes of the instructor " + userId + " filtered by the supplied class status: "
				+ statusFilter);
		return instructorService.findHistoricClassesByInstructorIdWithClassStatusFilter(userId, statusFilter);
	}
}
