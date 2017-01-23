package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.InstructorSchedule;
import com.wedevol.iclass.core.entity.ScheduleBasic;

/**
 * Instructor Schedule Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorScheduleService {

	List<InstructorSchedule> findAll();

	InstructorSchedule findById(Long scheduleId);

	InstructorSchedule create(InstructorSchedule schedule);

	void update(Long scheduleId, InstructorSchedule schedule);

	void delete(Long scheduleId);

	List<InstructorSchedule> findSchedulesForWeekByInstructorId(Long instructorId);
	
	List<ScheduleBasic> findSchedulesByCourseIdByDate(Long courseId, Date classDate);

	List<ScheduleBasic> findSchedulesByCourseIdByWeekDay(Long courseId, String weekDayStr);

}
