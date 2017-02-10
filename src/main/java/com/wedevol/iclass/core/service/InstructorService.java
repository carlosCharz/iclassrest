package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.view.request.UserView;
import com.wedevol.iclass.core.view.response.ClassFullInfo;
import com.wedevol.iclass.core.view.response.CourseFullInfo;
import com.wedevol.iclass.core.view.response.InstructorBasic;
import com.wedevol.iclass.core.view.response.InstructorView;
import com.wedevol.iclass.core.view.response.ScheduleBasic;

/**
 * Instructor Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorService {

	List<Instructor> findAll();

	Instructor findByEmail(String email);

	Instructor findByDeviceId(String deviceId);

	Instructor findById(Long userId);

	Instructor create(Instructor instructor);

	void update(Long userId, Instructor instructor);

	void delete(Long userId);

	void setUserInactive(Long userId);

	Instructor createInstructorWithCourse(UserView instructorView);

	List<Instructor> findInstructorsByCourseId(Long courseId);

	List<InstructorBasic> findInstructorsByCourseIdByDateTime(Long courseId, Date classDate, Integer startTime,
			Integer endTime);

	List<InstructorBasic> findInstructorsByCourseIdByWeekDayByTime(Long courseId, String weekDayStr, Integer startTime,
			Integer endTime);

	List<CourseFullInfo> findCoursesByInstructorIdWithCourseStatusFilter(Long instructorId, String courseStatusFilter);

	List<ScheduleBasic> findSchedulesByCourseIdByDate(Long courseId, Date classDate);

	List<ScheduleBasic> findSchedulesByCourseIdByWeekDay(Long courseId, String weekDayStr);

	List<ClassFullInfo> findComingClassesByInstructorIdByDateTimeWithClassStatusFilter(Long instructorId,
			Date actualDate, Integer actualTime, String classStatusFilter);

	List<ClassFullInfo> findHistoricClassesByInstructorIdWithClassStatusFilter(Long instructorId,
			String classStatusFilter);

	InstructorView getInstructorByIdWithFullInfo(Long userId);

	Instructor getInstructorByEmail(String email);
}
