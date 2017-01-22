package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;

/**
 * Instructor Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorService {

	List<Instructor> findAll();

	Instructor findByEmail(String email);

	Instructor findById(Long userId);

	Instructor create(Instructor instructor);

	void update(Long userId, Instructor instructor);

	void delete(Long userId);
	
	List<Instructor> findInstructorsByCourseId(Long courseId);

	List<InstructorBasic> findInstructorsByCourseIdByDateTime(Long courseId, Date classDate, Integer startTime,
			Integer endTime);

	List<InstructorBasic> findInstructorsByCourseIdByWeekDayByTime(Long courseId, String weekDayStr, Integer startTime,
			Integer endTime);
}
