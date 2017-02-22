package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.view.request.UserView;
import com.wedevol.iclass.core.view.response.ClassResponse;
import com.wedevol.iclass.core.view.response.CourseResponse;
import com.wedevol.iclass.core.view.response.InstructorBasic;
import com.wedevol.iclass.core.view.response.InstructorFull;
import com.wedevol.iclass.core.view.response.ScheduleBasic;

/**
 * Instructor Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorService extends BaseService<Instructor> {

	List<Instructor> findAll();

	Instructor findByEmail(String email);

	Instructor findByDeviceId(String deviceId);

	void setUserInactive(Long userId);

	Instructor createInstructorWithCourse(UserView instructorView);

	List<Instructor> findInstructorsByCourseId(Long courseId);

	List<InstructorBasic> findInstructorsByCourseIdByDateTime(Long courseId, Date classDate, Integer startTime,
			Integer endTime);

	List<InstructorBasic> findInstructorsByCourseIdByWeekDayByTime(Long courseId, String weekDayStr, Integer startTime,
			Integer endTime);

	List<CourseResponse> findCoursesByInstructorIdWithCourseStatusFilter(Long instructorId, String courseStatusFilter);

	List<ScheduleBasic> findSchedulesByCourseIdByDate(Long courseId, Date classDate);

	List<ScheduleBasic> findSchedulesByCourseIdByWeekDay(Long courseId, String weekDayStr);

	List<ClassResponse> findComingClassesByInstructorIdByDateTimeWithClassStatusFilter(Long instructorId,
			Date actualDate, Integer actualTime, String classStatusFilter);

	List<ClassResponse> findHistoricClassesByInstructorIdWithClassStatusFilter(Long instructorId,
			String classStatusFilter);

	InstructorFull getInstructorByIdWithFullInfo(Long userId);

	Instructor getInstructorByEmail(String email);
}
