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

import com.wedevol.iclass.core.entity.InstructorSchedule;
import com.wedevol.iclass.core.entity.ScheduleBasic;
import com.wedevol.iclass.core.service.InstructorManagerService;
import com.wedevol.iclass.core.service.InstructorScheduleService;
import com.wedevol.iclass.core.util.CommonUtil;

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
	
	@Autowired
	private InstructorManagerService insMgrService;

	/************* CRUD for instructor schedule ****************/

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<InstructorSchedule> findAll() {
		return scheduleService.findAll();
	}

	@RequestMapping(value = "/{scheduleId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public InstructorSchedule findById(@PathVariable Long scheduleId) {
		return scheduleService.findById(scheduleId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@Valid @RequestBody InstructorSchedule schedule) {
		scheduleService.create(schedule);
	}

	@RequestMapping(value = "/{scheduleId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long scheduleId, @Valid @RequestBody InstructorSchedule schedule) {
		scheduleService.update(scheduleId, schedule);
	}

	@RequestMapping(value = "/{scheduleId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long scheduleId) {
		scheduleService.delete(scheduleId);
	}
	
	/********* Courses & Instructors & Enrollment *************/

	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ScheduleBasic> findScheduleByCourseIdByDate(@RequestParam("courseId") Long courseId,
			@RequestParam("classDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date classDate) {
		logger.info("Find instructors of the course " + courseId + " in " + CommonUtil.dateToString(classDate));
		return insMgrService.findScheduleByCourseIdByDate(courseId, classDate);
	}
}
