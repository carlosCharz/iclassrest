package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.InstructorSchedule;

/**
 * Instructor Schedule Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorScheduleService {

	List<InstructorSchedule> findAll();

	InstructorSchedule findById(Long scheduleId);

	void create(InstructorSchedule schedule);

	void update(Long scheduleId, InstructorSchedule schedule);

	void delete(Long scheduleId);

}
