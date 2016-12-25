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

import com.wedevol.iclass.core.entity.InstructorSchedule;
import com.wedevol.iclass.core.service.InstructorScheduleService;

/**
 * Instructor Schedule Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/schedules")
public class InstructorScheduleController {

	@Autowired
	private InstructorScheduleService scheduleService;

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
}