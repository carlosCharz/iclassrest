package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.InstructorSchedule;
import com.wedevol.iclass.core.view.response.ScheduleBasic;

/**
 * Instructor Schedule Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorScheduleService extends BaseService<InstructorSchedule> {

	List<InstructorSchedule> findAll();

	List<InstructorSchedule> findSchedulesForWeekByInstructorId(Long instructorId);
	
	List<ScheduleBasic> findSchedulesByCourseIdByDate(Long courseId, Date classDate);

	List<ScheduleBasic> findSchedulesByCourseIdByWeekDay(Long courseId, String weekDayStr);

}
