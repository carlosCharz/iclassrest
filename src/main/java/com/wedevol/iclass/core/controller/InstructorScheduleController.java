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

import com.wedevol.iclass.core.entity.InstructorSchedule;
import com.wedevol.iclass.core.entity.ScheduleBasic;
import com.wedevol.iclass.core.service.InstructorScheduleService;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Instructor Schedule Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/schedules")
public class InstructorScheduleController {

	protected static final Logger logger = LoggerFactory.getLogger(InstructorScheduleController.class);

	@Autowired
	private InstructorScheduleService scheduleService;

	@ApiIgnore
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<InstructorSchedule> findAll() {
		return scheduleService.findAll();
	}

	@ApiIgnore
	@RequestMapping(value = "/{scheduleId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public InstructorSchedule findById(@PathVariable Long scheduleId) {
		return scheduleService.findById(scheduleId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public InstructorSchedule create(@Valid @RequestBody InstructorSchedule schedule) {
		return scheduleService.create(schedule);
	}

	@RequestMapping(value = "/{scheduleId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long scheduleId, @Valid @RequestBody InstructorSchedule schedule) {
		scheduleService.update(scheduleId, schedule);
	}

	@ApiIgnore
	@RequestMapping(value = "/{scheduleId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long scheduleId) {
		scheduleService.delete(scheduleId);
	}

	@RequestMapping(value = "/week", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<InstructorSchedule> findSchedulesForWeekByInstructorId(
			@RequestParam(value = "instructorId", required = true) Long instructorId) {
		logger.info("Find week schedule for the instructor " + instructorId);
		return scheduleService.findSchedulesForWeekByInstructorId(instructorId);
	}

	@ApiIgnore
	@Deprecated
	@RequestMapping(value = "/fetch2", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ScheduleBasic> findSchedulesByCourseIdByDate(
			@RequestParam(value = "courseId", required = true) Long courseId,
			@RequestParam(value = "classDate", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date classDate) {
		logger.info("Find schedules of the course " + courseId + " for an specific date: " + dateToString(classDate));
		return scheduleService.findSchedulesByCourseIdByDate(courseId, classDate);
	}

	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ScheduleBasic> findSchedulesByCourseIdByWeekDay(
			@RequestParam(value = "courseId", required = true) Long courseId,
			@RequestParam(value = "weekDay", required = true) String weekDay) {
		logger.info("Find schedules of the course " + courseId + " for week day: " + weekDay);
		return scheduleService.findSchedulesByCourseIdByWeekDay(courseId, weekDay);
	}
}
