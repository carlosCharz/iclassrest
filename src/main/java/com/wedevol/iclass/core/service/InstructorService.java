package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.CourseFullInfo;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;
import com.wedevol.iclass.core.entity.ScheduleBasic;
import com.wedevol.iclass.core.view.UserView;

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
	
	Instructor createInstructorWithCourse(UserView instructorView);
	
	List<Instructor> findInstructorsByCourseId(Long courseId);

	List<InstructorBasic> findInstructorsByCourseIdByDateTime(Long courseId, Date classDate, Integer startTime,
			Integer endTime);

	List<InstructorBasic> findInstructorsByCourseIdByWeekDayByTime(Long courseId, String weekDayStr, Integer startTime,
			Integer endTime);
	
	List<CourseFullInfo> findCoursesByInstructorIdWithCourseStatusFilter(Long instructorId, String courseStatusFilter);
	
	List<ScheduleBasic> findSchedulesByCourseIdByDate(Long courseId, Date classDate);

	List<ScheduleBasic> findSchedulesByCourseIdByWeekDay(Long courseId, String weekDayStr);
}
